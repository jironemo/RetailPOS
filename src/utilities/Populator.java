package utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
public class Populator {

static Connection con = DBConnector.getConnection();
	public static void setComboData(JComboBox<String> txtCategory) {

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT category_name from category");
			while(rs.next()){
				txtCategory.addItem(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtCategory.setSelectedItem("");
	}
}
