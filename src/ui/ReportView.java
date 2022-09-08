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
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class ReportView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6606094668711452282L;
	private JDateChooser chooserFirstDate,chooserLastDate;
	RowTable table = new RowTable();
	private JTextField txtInvoice;
	JTabbedPane tabbedPane;
	/**
	 * Create the panel.
	 */
	public ReportView() {
		setOpaque(false);
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
				{null, null, null, null, null},
			},
			new String[] {
				"Invoice No.", "Product Code", "Product Name", "Quantity", "Date"
			}
		));
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(84);
		table.getColumnModel().getColumn(1).setPreferredWidth(143);
		table.getColumnModel().getColumn(2).setPreferredWidth(275);
		table.getColumnModel().getColumn(3).setPreferredWidth(58);
		table.getColumnModel().getColumn(4).setPreferredWidth(85);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(20);
		table.setFont(new Font("Myamar Text", Font.PLAIN, 14));
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		populateReport();
		
		scrollPane.setViewportView(table);

		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(10, 82, 230, 225);
		add(tabbedPane);
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		tabbedPane.addTab("Filter By Date", null, panel, "Filter Sales by Date");
		tabbedPane.setEnabledAt(0, true);
		tabbedPane.setBackground(new Color(255, 205, 130));
		panel.setLayout(null);
		try {
			SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd");
			JLabel lblNewLabel = new JLabel("From");
			lblNewLabel.setBounds(15, 42, 98, 25);
			panel.add(lblNewLabel);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			chooserFirstDate = new JDateChooser();
			chooserFirstDate.setBounds(15, 65, 200, 35);
			panel.add(chooserFirstDate);
			chooserFirstDate.getCalendarButton().setLocation(94, 23);
			chooserFirstDate.setDate(df.parse(table.getValueAt(0, 4).toString()));
			
			JLabel lblTo = new JLabel("To");
			lblTo.setBounds(15, 111, 98, 25);
			panel.add(lblTo);
			lblTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			chooserLastDate = new JDateChooser();
			chooserLastDate.setBounds(15, 137, 200, 35);
			panel.add(chooserLastDate);
			chooserLastDate.setDate(df.parse(df.format(new Date())));
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		

		
		
		
		
		JLabel lblSelectFirstDate = new JLabel("Select Date");
		lblSelectFirstDate.setBounds(74, 11, 98, 25);
		panel.add(lblSelectFirstDate);
		lblSelectFirstDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		tabbedPane.addTab("Filter By Invoice", null, panel_1, null);
		tabbedPane.setEnabledAt(1, true);
		panel_1.setLayout(null);
		
		txtInvoice = new JTextField();
		txtInvoice.setBounds(10, 61, 205, 38);
		panel_1.add(txtInvoice);
		txtInvoice.setColumns(10);
		
		JLabel lblEnterInvoiceNumber = new JLabel("Enter Invoice Number");
		lblEnterInvoiceNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnterInvoiceNumber.setBounds(10, 32, 205, 25);
		panel_1.add(lblEnterInvoiceNumber);
		
		JButton btnFilter = new JButton("Filter");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtInvoice.getText().isEmpty() == true) {
					JOptionPane.showMessageDialog(null, "Please enter an invoice number!");
					
				}else {
					
					// TODO Auto-generated method stub
						int invoice = Integer.parseInt(txtInvoice.getText());
						getData(invoice);
				}
			}
		});
		btnFilter.setForeground(Color.WHITE);
		btnFilter.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnFilter.setBorder(null);
		btnFilter.setBackground(Color.BLACK);
		btnFilter.setBounds(10, 151, 205, 35);
		panel_1.add(btnFilter);
		
		JButton btnNewButton = new JButton("Export Spreadsheet");
		btnNewButton.setBounds(10, 318, 225, 35);
		add(btnNewButton);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setBorder(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createWorkbook();
			}
		});
		
		
		

	}
	protected void getData(int invoice) {
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
		String query = String.format("CALL GET_SALES_BY_INVOICE('%1s')",invoice);
		try {
			rs = s.executeQuery(query);
			while(rs.next()) {
				Object[] result = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5)};
				d.addRow(result);
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
				Object[] result = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5)};
				d.addRow(result);
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	protected void createWorkbook() {
		String ext = null;
		
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
				File f;
				if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					if(tabbedPane.getSelectedIndex() == 0) {
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						String from = format.format(chooserFirstDate.getDate());
						String to = format.format(chooserLastDate.getDate());
						f = new File(fileChooser.getSelectedFile()+"/"+from+" to "+to+ext);
					}else {
						f = new File(fileChooser.getSelectedFile()+"/"+"Invoice#"+txtInvoice.getText()+ext);
					}
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
				Object[] result = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5)};
				d.addRow(result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
