package Controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Models.UnidadDidactica;
import Utilidades.MySqlConnection;

public class Controller implements IController {
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private CallableStatement callableStatement = null;
        
        //Querys
        final String INSERTunidadDidactica = "INSERT INTO UnidadDidactica (id, acronimo, titulo, evaluacion, descripcion) VALUES (?, ?, ?, ?, ?)";

	
        
            @Override
        public boolean crearUnidad(int id, String acronimo, String titulo, String evaluacion, String descripcion) {
		boolean added = false;
		try {
			connection = MySqlConnection.getInstance("entrenador").getConnection();

			statement = connection.prepareStatement(INSERTunidadDidactica);
			statement.setInt(1, id);
			statement.setString(2, acronimo);
			statement.setString(3, titulo);
			statement.setString(4, evaluacion);
			statement.setString(5, descripcion);
			if (statement.executeUpdate() > 0) {
				added = true;
				System.out.println("Data inserted!");
			} else {
				System.out.println("Failed!");
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} 
		return added;
	}


    @Override
    public void crearConvocatoria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void crearEnunciado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void consultarEnunciadosPorUnidad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void consultarConvocatoriasConEnunciado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visualizarTextoEnunciado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void asignarEnunciadoConvocatoria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void connectionDB() {
        String url = "jdbc:mysql://localhost:3306/tu_basededatos";
        String user = "root";
        String password = "abcd*1234";

        Connection connection = null;

        try {
            // Cargar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión
            connection = DriverManager.getConnection(url, user, password);
            // Si la conexión se establece con éxito
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el controlador JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos.");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión.");
                    e.printStackTrace();
                }
            }
        }
    }


    
}
