package controllers;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utilities.NonEditTableModel;
import javax.swing.table.DefaultTableModel;

import utilities.DBConnector;

public class StocksController {
	public static DefaultTableModel getSales( ) {
		DefaultTableModel d = new NonEditTableModel();
		
		Connection con = new DBConnector().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT product_code,concat(product_name,\" \" ,product_unit.unit_name),category.category_name,product.unit_instock,`unit_price(MMK)`,product.discount_percentage FROM pos.product inner join pos.category inner join pos.product_unit where pos.product.category_id = pos.category.category_id and product_unit.unit_id = product.unit_id;");
			setHeaders(d);
			while(rs.next()){
				d.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6)});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	
	private static void setHeaders(DefaultTableModel d ) {
		Object[] columns = {"Product Code","Product","Category","Stock","Unit Price","Discount Percentage"};
		
		for(Object column: columns) {
			d.addColumn(column);
		}
		
	}
	
	public static List<String> getComboData() {
		List<String> g = new ArrayList<String>();
		Connection con = new DBConnector().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT category_name from category");
			while(rs.next()){
				g.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return g;	
		};

	}

