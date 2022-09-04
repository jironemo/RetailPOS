package utilities;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
public class DBConnector {

	static Connection con=null;
    public static Connection getConnection()
    {
        if (con != null) {
        	System.out.println("Con null: "+con!=null);
        	return con;
        }
        // get db, user, pass from settings file
        return estaCon();
    }
	private static Connection estaCon() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://192.168.122.98/pos?user=root&password=root");
			//con = DriverManager.getConnection("jdbc:mysql://localhost/pos?user=admin&password=root");
			System.out.println("Connection Established");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	public static void closeConnection() {
		try {
			if(con.isClosed() == false) {
				con.close();
				System.out.println("Connection Successfully Closed");
			}
		}catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
