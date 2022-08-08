package controllers;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utilities.NonEditTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import utilities.DBConnector;

public class StocksController {
	public static DefaultTableModel getSales( ) {
		DefaultTableModel d = new NonEditTableModel();
		
		Connection con = new DBConnector().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT product_code,product_name,category.category_name,product.unit_instock,`unit_price(MMK)` FROM pos.product inner join pos.category where pos.product.category_id = pos.category.category_id;");
			setHeaders(d);
			while(rs.next()){
				d.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5)});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	
	private static void setHeaders(DefaultTableModel d ) {
		Object[] columns = {"Product Code","Product Name","Category","Stock","Unit Price"};
		
		for(Object column: columns) {
			d.addColumn(column);
		}
		
	}

}
