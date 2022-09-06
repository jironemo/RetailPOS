package ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

import utilities.DBConnector;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import utilities.RowTable;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class ReportView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6606094668711452282L;

	RowTable table = new RowTable();
	/**
	 * Create the panel.
	 */
	public ReportView() {
		setSize(1050, 596);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(30, 57, 668, 470);
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
		
		JLabel lblNewLabel = new JLabel("Select Last Date");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(399, 21, 98, 25);
		add(lblNewLabel);

		JDateChooser chooserFirstDate = new JDateChooser();
		chooserFirstDate.setBounds(138, 16, 191, 35);
		add(chooserFirstDate);
		JDateChooser chooserLastDate = new JDateChooser();
		chooserLastDate.getDateEditor().addPropertyChangeListener("date",new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
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
				if(chooserFirstDate.getDate() == null) {
					try {
						String date = new SimpleDateFormat("yyyy-MM-dd").format(evt.getNewValue());
						String query = String.format("CALL GET_SALES_BY_DATE('%1s')",date);
						rs = s.executeQuery(query);
						while(rs.next()) {
							Object[] k = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)};
							d.addRow(k);
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
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
		chooserLastDate.setBounds(507, 16, 191, 35);
		add(chooserLastDate);
		
		JLabel lblSelectFirstDate = new JLabel("Select First Date");
		lblSelectFirstDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectFirstDate.setBounds(30, 21, 98, 25);
		add(lblSelectFirstDate);
		
		
		

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
