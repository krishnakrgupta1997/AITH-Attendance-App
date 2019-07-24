package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil extends Configs {
	static Connection dbconnection;
	public static Connection getDataBaseConnection() {
		String connectionString = "jdbc:mysql://"+ Configs.dbhost + ":" + Configs.dbport + "/" + Configs.dbname + "?useSSL=false" ;
		/*
		try {
			Class.forName("jdbc.mysql.driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		try {
			dbconnection = DriverManager.getConnection(connectionString, Configs.dbuser, Configs.dbpass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dbconnection;		
	}
}
