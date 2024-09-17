package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Usaremos esta clase para conectarnos al usuario administrador que ya hemos creado en la base de datos MysQL. Tenemos tres de estas conexiones.
public class DatabaseConnection{

	private static Connection connection = null;

	static {
		String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
		String user = "root";
		String pass = "abcd*1234*";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	* 	El método devuelve una conexión a la base de datos como un usuario policia.
	* 	@return Connection connection
	*/
	
	public static Connection getConnection() {
		return connection;
	}
}