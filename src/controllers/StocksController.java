package controllers;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilities.NonEditTableModel;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import utilities.DBConnector;

public class StocksController {
	/**
	 * @wbp.parser.entryPoint
	 */

	static Connection con = new DBConnector().getConnection();
	static String[] values;
	public static DefaultTableModel getData( ) {
		DefaultTableModel d = new NonEditTableModel();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT product_code,product_name,product_unit.unit_name,category.category_name,product.unit_instock,`unit_price(MMK)`,product.discount_percentage FROM pos.product inner join pos.category inner join pos.product_unit where pos.product.category_id = pos.category.category_id and product_unit.unit_id = product.unit_id;");
			setHeaders(d);
			while(rs.next()){
				d.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	private static void setHeaders(DefaultTableModel d ) {
		Object[] columns = {"Product Code","Product","Unit","Category","Stock","Unit Price","Discount Percentage"};
		
		for(Object column: columns) {
			d.addColumn(column);
		}
		
	}
	@SuppressWarnings("unchecked")
	public static void getStocks(JTable table,Component[] textfield) {
		
		for(int i = 0; i < table.getColumnCount();i++) {
			Object selected = table.getValueAt(table.getSelectedRow(), i);
			if(i == 3) {
				((JComboBox<String>)textfield[i]).setSelectedItem(selected.toString());
			}
			else ((JTextField)textfield[i]).setText(selected.toString());
		}
	}
	

	@SuppressWarnings("unchecked")
	public static void setTextBoxesNull(Component[] textfields) {

		for(Component textfield : textfields) {
			if(textfield.getClass() == JComboBox.class) {
				((JComboBox<String>)textfield).setSelectedItem("");
			}
			else ((JTextField)textfield).setText(null);
		}
	}
	@SuppressWarnings("unchecked")
	private static  void getValues(Component[] textfields) {
		values= new String[textfields.length];
		for(int i = 0; i < textfields.length;i++) {
			
			if(i == 3) {
				values[i] = ((JComboBox<String>)textfields[i]).getSelectedItem().toString();
			}
			else values[i] = ((JTextField)textfields[i]).getText();
		}
	}

	public static void deleteData(JTable table, Component[] textfields) {
		// TODO Auto-generated method stub
		getValues(textfields);

		int result= JOptionPane.showConfirmDialog(null,"Are you sure you want to delete?" , "Delete", JOptionPane.YES_NO_OPTION);
		if (result ==JOptionPane.OK_OPTION) {
			try {
				String query = "delete from product where product_code ='" +values[0] +"'";
				PreparedStatement s = con.prepareStatement(query);
				if(s.executeUpdate() == 1) {
					JOptionPane.showMessageDialog(null, "Delete Successful","Successful!",JOptionPane.INFORMATION_MESSAGE);
				}
				((DefaultTableModel)table.getModel()).removeRow(table.getSelectedRow());
				StocksController.setTextBoxesNull(textfields);	
			}catch(SQLException e){
				e.printStackTrace();
			}
				
		}
	}

	private static void updateData() {
	
		String format = "UPDATE `pos`.`product`"
				+ "SET"
				+ "`product_name` ='%2$s',"
				+ "`unit_id` = GET_UNIT('%3$s'),"
				+ "`category_id` =GET_CATEGORY('%4$s'),"
				+ "`unit_instock` = '%5$s',"
				+ "`unit_price(MMK)` ='%6$s',"
				+ "`discount_percentage` ='%7$s'"
				+ "WHERE `product_code` ='%1$s';";
		
		String sql =String.format(format, (Object[])values);
		PreparedStatement s;
		try {
			s = con.prepareStatement(sql);
			int k = s.executeUpdate();
			if(k == 1) {
				JOptionPane.showMessageDialog(null, "Update Successful","Successful!",JOptionPane.INFORMATION_MESSAGE);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateData(JLabel[] labels,Component[] textfields) {
		getValues(textfields);
		// TODO Auto-generated method stub
		String message = "Please confirm update Data:\n";
		for(int i = 1;i< textfields.length;i++) {
			message += labels[i].getText() + ":\t"+ values[i] + "\n";
		}
		int result = JOptionPane.showConfirmDialog(null, message,"",JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION) {
			updateData();
		}
	}
	
}
	
