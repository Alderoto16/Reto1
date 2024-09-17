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
import Utilidades.Util;
import java.sql.Date;

public class Controller implements IController {
    
    private Connection connection = null;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private CallableStatement callableStatement = null;
        
        //Querys
        final String INSERTunidadDidactica = "INSERT INTO UnidadDidactica (id, acronimo, titulo, evaluacion, descripcion) VALUES (?, ?, ?, ?, ?)";
	final String INSERTenunciado = "INSERT INTO Enunciado (id, decripcion, nivel, disponible, ruta, convocatoria_examen) VALUES (?, ?, ?, ?, ?, ?)";
        final String INSERTconvocatoria = "INSERT INTO Convocatoria (convocatoria, descripcion, fecha, curso) VALUES (?, ?, ?, ?)";
       

        
            @Override
        public boolean crearUnidad() {
		boolean added = false;
		try {   
                    connectionDB();
                    System.out.println("Introduce el ID de la unidad:");
                    int id = Util.leerInt();
                    System.out.println("Introduce el acronimo de la unidad:");
                    String acronimo = Util.introducirCadena();
                    System.out.println("Introduce el titulo de la unidad:");
                    String titulo = Util.introducirCadena();
                    System.out.println("Introduce la evaluacion de la unidad:");
                    String evaluacion = Util.introducirCadena();
                    System.out.println("Introduce la descripcion de la unidad:");
                    String descripcion = Util.introducirCadena();
                    
                    
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
    public boolean crearConvocatoria(String convocatoria, String descripcion, Date fecha, String curso) {
        boolean added = false;
		try {
                        connectionDB();
			statement = connection.prepareStatement(INSERTunidadDidactica);
			statement.setString(1, convocatoria);
			statement.setString(2, descripcion);
			statement.setDate(3, fecha);
			statement.setString(4, curso);
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
    public boolean crearEnunciado() {
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

    public void connectionDB() {
        String url = "jdbc:mysql://localhost:3306/examendb?serverTimezone=Europe/Madrid";
        String user = "root";
        String password = "abcd*1234";
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
        }
    }
}
