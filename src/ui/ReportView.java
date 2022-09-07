package ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.*;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import utilities.DBConnector;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import utilities.RowTable;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;

public class ReportView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6606094668711452282L;
	private JDateChooser chooserFirstDate,chooserLastDate;
	RowTable table = new RowTable();
	/**
	 * Create the panel.
	 */
	public ReportView() {
		setSize(1050, 596);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(29, 74, 668, 470);
		add(scrollPane);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Invoice No.", "Product Code", "Product Name", "Quantity", "Date"
			}
		));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(20);
		table.setFont(new Font("Myamar Text", Font.PLAIN, 14));
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		populateReport();
		
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("From");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(115, 33, 98, 25);
		add(lblNewLabel);

		chooserFirstDate = new JDateChooser();
		chooserFirstDate.getCalendarButton().setLocation(94, 23);
		chooserFirstDate.setBounds(154, 28, 115, 35);
		add(chooserFirstDate);
		chooserLastDate = new JDateChooser();
		
		chooserLastDate.getDateEditor().addPropertyChangeListener("date",new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
					if(chooserFirstDate.getDate().compareTo(chooserLastDate.getDate())>0) {
						JOptionPane.showMessageDialog(null, "Start date must be lower than or equal to End Date");
						chooserFirstDate.setDate(new Date());
						chooserLastDate.setDate(new Date());
					}else {
						Statement s = null;
						ResultSet rs = null;
						DefaultTableModel d = (DefaultTableModel)table.getModel();
						d.setRowCount(0);
						try {
							s = DBConnector.getConnection().createStatement();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// TODO Auto-generated method stub
							String lastdate = new SimpleDateFormat("yyyy-MM-dd").format(evt.getNewValue());
							String firstdate = new SimpleDateFormat("yyyy-MM-dd").format(chooserFirstDate.getDate());
							String query = String.format("CALL GET_SALES_BY_2_DATES('%1s','%2s')",firstdate,lastdate);
							System.out.println(firstdate);
							try {
								
								rs = s.executeQuery(query);
								while(rs.next()) {
									Object[] k = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)};
									d.addRow(k);
								}
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
			}
		});
		chooserFirstDate.getDateEditor().addPropertyChangeListener(((Component) chooserLastDate.getDateEditor()).getPropertyChangeListeners()[0]);
		chooserLastDate.setBounds(314, 28, 115, 35);
		add(chooserLastDate);
		
		JLabel lblSelectFirstDate = new JLabel("Select Date");
		lblSelectFirstDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectFirstDate.setBounds(29, 33, 98, 25);
		add(lblSelectFirstDate);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTo.setBounds(291, 33, 98, 25);
		add(lblTo);
		
		JButton btnNewButton = new JButton("Generate Report");
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createWorkbook();
			}
		});
		btnNewButton.setBounds(439, 28, 200, 35);
		add(btnNewButton);
		try {
			SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd");
			chooserFirstDate.setDate(df.parse(table.getValueAt(0, 4).toString()));
			chooserLastDate.setDate(df.parse(df.format(new Date())));
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		

	}
	protected void createWorkbook() {
		// TODO Auto-generated method stub
		  try (XSSFWorkbook workbook = new XSSFWorkbook()) {
			  Sheet sheet = workbook.createSheet();
			  sheet.createRow(0);
			  org.apache.poi.ss.usermodel.Font k = workbook.createFont();
			  k.setFontName("Tahoma");
			  k.setFontHeightInPoints((short) 14);
			  CellStyle cellstyle = workbook.createCellStyle();
			  cellstyle.setFont(k);
			  sheet.getRow(0).setRowStyle(cellstyle);
			  sheet.getRow(0).setHeightInPoints((float)20);
			  cellstyle.setVerticalAlignment(VerticalAlignment.CENTER);
			  for(int col = 0; col < table.getColumnCount();col++) {
				  Cell c = sheet.getRow(0).createCell(col);
				  c.setCellValue(table.getColumnName(col));
			  }
			  for(int i = 0; i < table.getRowCount();i++){
				  sheet.createRow(i+1); 
				  for(int j = 0; j < table.getColumnCount();j++) {
					  Cell c = sheet.getRow(i+1).createCell(j);
					  c.setCellValue(table.getValueAt(i,j).toString());
					  sheet.autoSizeColumn(j);
				  }
			  }
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
				if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String from = format.format(chooserFirstDate.getDate());
					String to = format.format(chooserLastDate.getDate());
					File f = new File(fileChooser.getSelectedFile()+"/"+from+" to "+to+".xlsx");
					f.createNewFile();
					
					workbook.write(new FileOutputStream(f));
					
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
	}
	public void populateReport() {
		try {
			DefaultTableModel d = (DefaultTableModel) table.getModel();
			Statement s = DBConnector.getConnection().createStatement();
			ResultSet rs = s.executeQuery("CALL GET_SALES()");
			int rows = d.getRowCount();
			System.out.println(rows);
			d.setRowCount(0);
			while(rs.next()) {
				Object[] result = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)};
				d.addRow(result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
