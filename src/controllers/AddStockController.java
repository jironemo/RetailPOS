package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ui.StocksPanel;
import utilities.DBConnector;

public class AddStockController {
	static Connection con = new DBConnector().getConnection();
	public static void addToDB(String[] values) {
		// TODO Auto-generated method stub
		String format = "INSERT INTO `pos`.`product`"
				+ "(`product_code`,`product_name`,`unit_id`,`category_id`,`unit_instock`,"
				+ "`unit_price(MMK)`,`discount_percentage`,`user_id`)"
				+ "VALUES"
				+ "(\r\n"
				+ "'%1$s','%2$s',GET_UNIT('%3$s'),GET_CATEGORY('%4$s'),"
				+ "'%5$s','%6$s','%7$s',1);";
		String sql = String.format(format, (Object[])values);
		try {
			PreparedStatement p = con.prepareStatement(sql);
			int result = p.executeUpdate();
			if(result == 1) {
				JOptionPane.showMessageDialog(null, "Insert successful", "Successful", JOptionPane.INFORMATION_MESSAGE);

				StocksPanel.table.setModel(StocksController.getData(StocksPanel.table));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
