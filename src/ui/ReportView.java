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
import java.awt.ComponentOrientation;
import javax.swing.ScrollPaneConstants;
import utilities.RowTable;
import java.awt.Font;

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
		scrollPane.setBounds(30, 46, 668, 481);
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
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		scrollPane.setViewportView(table);
		populateReport();

	}
	public void populateReport() {
		try {
			DefaultTableModel d = (DefaultTableModel) table.getModel();
			Statement s = DBConnector.getConnection().createStatement();
			ResultSet rs = s.executeQuery("CALL GET_SALES()");
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
