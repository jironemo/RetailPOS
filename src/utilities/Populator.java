package utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
public class Populator {

static Connection con = new DBConnector().getConnection();
	public static void setComboData(JComboBox<String> txtCategory) {

		List<String> combodata = new ArrayList<String>();
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
		for(int i = 0; i < combodata.size();i++) {
		}
		txtCategory.setSelectedItem("");
	}
}
