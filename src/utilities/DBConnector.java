package utilities;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
public class DBConnector {

	Connection con = null;
	public Connection getConnection() {
		try {		
			con = DriverManager.getConnection("jdbc:mysql://192.168.137.1:3306/pos?user=root&password=root");
			System.out.println("Connection Estabilshed");	
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return con;
	}

	void closeConnection() {
		try {
			if(con.isClosed() == false) {
				con.close();
			}
		}catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
