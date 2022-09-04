package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import utilities.DBConnector;

public class AddSaleController {
	
	public String[] getRowOfData(String item_code) {
		
		try {
			String query = "select product_code, product_name,unit_name,`unit_price(MMK)`,discount_percentage from product inner join product_unit where product.unit_id = product_unit.unit_id and product_code = '"+item_code+ "'";
			Statement s = DBConnector.getConnection().createStatement();
			ResultSet rs = s.executeQuery(query);
			if(rs.next()) {

				if(rs.getDouble("discount_percentage")>0) {
					String[] tr = {rs.getString(1),rs.getString(2),rs.getString(3),Double.toString(rs.getDouble(4)-(rs.getDouble(4)*(rs.getDouble(5)/100))),null};
					return tr;
				}
				else {
					  String[] tr ={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),null};
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

	@SuppressWarnings("null")
	public void processSale(JTable table) {
		// TODO Auto-generated method stub
		int rowCount = table.getRowCount();
		String[][] listofItems = new String[rowCount][2];
		System.out.println(rowCount);
		int i = 0;
		try {
		Statement s = DBConnector.getConnection().createStatement();
		s.execute("INSERT INTO invoice (payment_type,date_recorded) values (0,NOW())");
		
		while( i < rowCount) {
			listofItems[i][0] = (String) table.getValueAt(i, 0);
			listofItems[i][1] = (String) table.getValueAt(i, 4);

				String format = "CALL PROCESS_SALE(GET_PRODUCT_ID('%1s'),%2s)";
				s.execute(String.format(format,listofItems[i][0] ,listofItems[i][1]));
				
				i++;
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
