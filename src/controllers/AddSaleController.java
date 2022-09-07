package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import utilities.DBConnector;

public class AddSaleController {
	
	public String[] getRowOfData(String item_code) {
		
		try {
			System.out.println("CODE:"+item_code);
			String query = "select product_code, product_name,unit_name,`unit_price(MMK)`,discount_percentage,product.unit_instock from product inner join product_unit where product.unit_id = product_unit.unit_id and (product_code = \""+item_code+ "\" or product_name = \""+item_code+"\")";
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
			}else return(null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}

	}

	public void addRowToTable(JTable table, Object[] k) {
		DefaultTableModel t = (DefaultTableModel)table.getModel();
		t.addRow(k);
	}


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
				listofItems[i][0] = table.getValueAt(i, 1).toString();
					listofItems[i][1] = table.getValueAt(i, 4).toString();
					System.out.println("ID:"+ listofItems[i][0]);
				String format = "CALL PROCESS_SALE(GET_PRODUCT_ID(\"%1s\"),\"%2s\")";
				s.execute(String.format(format,listofItems[i][0] ,listofItems[i][1]));
				i++;
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public int getRemainingQuantity(String code) {
		// TODO Auto-generated method stub
		try {
			String query = "select product.unit_instock from product where (product_code = \""+code+"\" or product_name = \""+code+"\");";
			Statement s = DBConnector.getConnection().createStatement();
			ResultSet rs = s.executeQuery(query);
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Wrong Item Code");
		}
		
		return 0;
	}

	public String[] getSuggestions(String input) {
		String[] output = {};
		try {
			String query = "select product.product_name from product where product_code like \""+input+"%\" or product_name like \""+input+"%\";";
			Statement s = DBConnector.getConnection().createStatement();
			ResultSet rs = s.executeQuery(query);
			output = new String[9];
			int i = 0;
			while(rs.next() && i < 9) {
				output[i] = rs.getString(1);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//JOptionPane.showMessageDialog(null, "Wrong Item Code");
			e.printStackTrace();
		}
		return output;
		// TODO Auto-generated method stub
		
	}
	
	

}
