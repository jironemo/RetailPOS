package controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import utilities.DBConnector;
import utilities.Dehasher;

public class LoginController {
	public static boolean handleLogin(String txtUser, String txtPass) {
		Connection con = new DBConnector().getConnection();
		try {
			String query = "select "
					+ "(password) from pos.user where username = '" +txtUser+"'";
			
			Statement stmt = con.createStatement();
			stmt.executeQuery(query);
			stmt.getResultSet().next();
			String password = stmt.getResultSet().getString(1);
			System.out.println("PASSWORD:" + password);
			
			if(Dehasher.getMd5(Dehasher.getMd5(txtPass)).equals(password)) {
				return true;
			}else {
				JOptionPane.showMessageDialog( null, "Username and password do not match.", "Error",1, null);
				
			}
			
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog( null, "Username does not exist", "Error",1, null);
		}
		return false;
	}
}
