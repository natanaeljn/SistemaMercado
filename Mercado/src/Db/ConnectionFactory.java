package Db;

import java.sql.Connection;
import java.sql.DriverManager;

/*classe para fazer as conexoes*/
public class ConnectionFactory {
	private static String url = "jdbc:mysql://localhost:3306/mercadonovo";
	private static String password = "nata35218284" ; 
	private static String user = "root";
	private static Connection connection = null ;
	
	static{
		conectar();
	}
	private static void conectar() {
		try {
			if(connection == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			connection.setAutoCommit(false);
			System.out.println("conexao sucedida");
		}
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		return connection;
	}
}
