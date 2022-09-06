package ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
import java.awt.event.ActionEvent;

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
		
		JComboBox<String> comMonth = new JComboBox<String>();
		comMonth.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		comMonth.setEditable(true);
		comMonth.setBounds(76, 6, 101, 35);
		add(comMonth);
		
		JComboBox<String> comDay = new JComboBox<String>();
		comDay.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comDay.setEditable(true);
		comDay.setBounds(249, 6, 57, 35);
		add(comDay);
		
		JComboBox<String> comYear = new JComboBox<String>();
		comYear.setModel(new DefaultComboBoxModel<String>(new String[] {"2020", "2021", "2022"}));
		comYear.setEditable(true);
		comYear.setBounds(381, 6, 113, 35);
		add(comYear);
		
		JLabel lblNewLabel = new JLabel("Month");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(30, 11, 57, 25);
		add(lblNewLabel);
		
		JLabel lblDay = new JLabel("Day");
		lblDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDay.setBounds(214, 11, 25, 25);
		add(lblDay);
		
		JLabel lblYea = new JLabel("Year");
		lblYea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblYea.setBounds(349, 11, 27, 25);
		add(lblYea);
		
		JButton btnNewButton = new JButton("Filter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement s= DBConnector.getConnection().createStatement();
					String date = comYear.getSelectedItem().toString() + "-" + (comMonth.getSelectedItem().toString()) + "-" +(comDay.getSelectedItem().toString());
					String query = String.format("CALL GET_SALES_BY_DATE('%1s')",date);
					ResultSet rs = s.executeQuery(query);
					DefaultTableModel d = (DefaultTableModel)table.getModel();
					d.setRowCount(0);
					while(rs.next()) {
						Object[] k = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)};
						d.addRow(k);
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(507, 12, 89, 23);
		add(btnNewButton);
		

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
