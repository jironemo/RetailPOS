package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import utilities.DBConnector;

public class AddSaleController {
	
	public String[] getRowOfData(String item_code,int quantity) {
		
		try {
			Connection c = new DBConnector().getConnection();
			String query = "select product_code, product_name,unit_name,`unit_price(MMK)`,discount_percentage from product inner join product_unit where product.unit_id = product_unit.unit_id and product_code = '"+item_code+ "'";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(query);
			if(rs.next()) {

				if(rs.getDouble("discount_percentage")>0) {
					String[] tr = {rs.getString(1),rs.getString(2),rs.getString(3),Double.toString(rs.getDouble(4)-(rs.getDouble(4)*(rs.getDouble(5)/100))),Integer.toString(quantity)};
					return tr;
				}
				else {
					  String[] tr ={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),Integer.toString(quantity)};
					  return tr;
				}
			}
			else return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}

	}

	public void addRowToTable(JTable table, Object[] k) {
		DefaultTableModel t = (DefaultTableModel)table.getModel();
		t.addRow(k);
	}

}
