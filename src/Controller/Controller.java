package Controller;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Dificultad;
import Models.UnidadDidactica;
import Utilidades.MySqlConnection;



public class Controller implements IController {
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private CallableStatement callableStatement = null;
        
        //Querys
        final String INSERTunidadDidactica = "INSERT INTO UnidadDidactica (id, acronimo, titulo, evaluacion, descripcion) VALUES (?, ?, ?, ?, ?)";

	
        
        
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

    @Override
    public boolean crearUnidad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
        
        
}
