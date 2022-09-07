package ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.border.EmptyBorder;

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
		scrollPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(245, 83, 668, 470);
		add(scrollPane);
		table.setSelectionForeground(new Color(204, 204, 204));
		table.setShowGrid(false);
		table.setShowVerticalLines(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Invoice No.", "Product Code", "Product Name", "Quantity", "Date"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(84);
		table.getColumnModel().getColumn(1).setPreferredWidth(190);
		table.getColumnModel().getColumn(2).setPreferredWidth(207);
		table.getColumnModel().getColumn(3).setPreferredWidth(86);
		table.getColumnModel().getColumn(4).setPreferredWidth(85);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(20);
		table.setFont(new Font("Myamar Text", Font.PLAIN, 14));
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		populateReport();
		
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("From");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(327, 42, 98, 25);
		add(lblNewLabel);

		chooserFirstDate = new JDateChooser();
		chooserFirstDate.getCalendarButton().setLocation(94, 23);
		chooserFirstDate.setBounds(366, 37, 127, 35);
		add(chooserFirstDate);
		chooserLastDate = new JDateChooser();
		try {
			SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd");
			chooserFirstDate.setDate(df.parse(table.getValueAt(0, 4).toString()));
			chooserLastDate.setDate(df.parse(df.format(new Date())));
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		chooserLastDate.getDateEditor().addPropertyChangeListener("date",new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
					if(chooserFirstDate.getDate().compareTo(chooserLastDate.getDate())>0) {
						JOptionPane.showMessageDialog(null, "Start date must be lower than or equal to End Date :last");
						try {
							SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd");
							chooserFirstDate.setDate(df.parse(table.getValueAt(0, 4).toString()));
							chooserLastDate.setDate(df.parse(df.format(new Date())));
						} catch (ParseException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}else {
						
						// TODO Auto-generated method stub
							String lastdate = new SimpleDateFormat("yyyy-MM-dd").format(evt.getNewValue());
							String firstdate = new SimpleDateFormat("yyyy-MM-dd").format(chooserFirstDate.getDate());
							getData(firstdate,lastdate);
					}
			}
		});
		chooserFirstDate.getDateEditor().addPropertyChangeListener("date",new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(chooserFirstDate.getDate().compareTo(chooserLastDate.getDate())>0) {
					JOptionPane.showMessageDialog(null, "Start date must be lower than or equal to End Date :first");
					try {
						SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd");
						chooserFirstDate.setDate(df.parse(table.getValueAt(0, 4).toString()));
						chooserLastDate.setDate(df.parse(df.format(new Date())));
					} catch (ParseException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}else {
					
					// TODO Auto-generated method stub
						String firstdate = new SimpleDateFormat("yyyy-MM-dd").format(evt.getNewValue());
						String lastdate = new SimpleDateFormat("yyyy-MM-dd").format(chooserLastDate.getDate());
						getData(firstdate,lastdate);
				}
			}
		});
		chooserLastDate.setBounds(526, 37, 127, 35);
		add(chooserLastDate);
		
		JLabel lblSelectFirstDate = new JLabel("Select Date");
		lblSelectFirstDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectFirstDate.setBounds(245, 42, 98, 25);
		add(lblSelectFirstDate);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTo.setBounds(503, 42, 98, 25);
		add(lblTo);
		
		JButton btnNewButton = new JButton("Generate Report");
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createWorkbook();
			}
		});
		btnNewButton.setBounds(713, 36, 200, 35);
		add(btnNewButton);
		
		
		

	}
	protected void getData(String firstdate, String lastdate) {
		// TODO Auto-generated method stub
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
		String query = String.format("CALL GET_SALES_BY_2_DATES('%1s','%2s')",firstdate,lastdate);
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
					Desktop.getDesktop().open(f);
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
