package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class connectDB {
	public static Connection accessDataBase() {
		Connection con = null;
		try {
			String url = "jdbc:sqlserver://localhost:1433;databasename=QLLop;encryty=false;";
			String user = "sa";
			String password = "03082004Vu";
			con =  DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return con;
	}
}
