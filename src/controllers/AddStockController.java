package controllers;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ui.StocksPanel;
import utilities.DBConnector;

public class AddStockController {
	public static boolean addToDB(String[] values) {
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
			PreparedStatement p = DBConnector.getConnection().prepareStatement(sql);
			int result = p.executeUpdate();
			if(result == 1) {
				JOptionPane.showMessageDialog(null, "Insert successful", "Successful", JOptionPane.INFORMATION_MESSAGE);
				StocksPanel.table.setModel(StocksController.getData(StocksPanel.table));
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Error when inserting.\n Possible Error: Product Code already exists.\n Please Check your data carefully","Error",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return false;
	}

}
