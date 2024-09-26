/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Controller.IController;
import Models.ConvocatoriaExamen;
import Models.Dificultad;
import Models.Enunciado;
import Models.UnidadDidactica;
import Utilidades.DBConnection;
import Utilidades.Util;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Omar
 */
public class DAO implements IDao {

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    // all queries here 
    final String INSERTENUNCIADO = "INSERT INTO Enunciado (descripcion, nivel, disponible, ruta) VALUES ( ?, ?, ?, ?)";
    final String INSERTunidadDidactica = "INSERT INTO UnidadDidactica (acronimo, titulo, evaluacion, descripcion) VALUES (?, ?, ?, ?)";
    final String INSERTconvocatoria = "INSERT INTO ConvocatoriaExamen (convocatoria, descripcion, fecha, curso, enunciadoID) VALUES (?, ?, ?, ?, ?)";

    private final String GETFILEPATH = "SELECT ruta FROM Enunciado WHERE id = ?";
    private final String GETAllENUNCIADOSIDS = "SELECT id FROM Enunciado";
    private final String GETconvocatorias = "SELECT * FROM ConvocatoriaExamen";
    private final String RETURN_ENUNCIADOS_UNIDAD
                    = "SELECT E.id, E.descripcion, E.nivel, E.disponible, E.ruta, CE.convocatoria "
                    + "FROM Enunciado E "
                    + "JOIN UnidadDidactica_Enunciado UDE ON E.id = UDE.enunciado_id "
                    + "JOIN UnidadDidactica UD ON UDE.unidad_id = UD.id "
                    + "LEFT JOIN ConvocatoriaExamen CE ON E.id = CE.enunciadoID "
                    + "WHERE UD.acronimo = ?;";

    @Override
    public boolean crearConvocatoria(ConvocatoriaExamen convExamen) {
        boolean added = false;
        try {

            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(INSERTconvocatoria);
            statement.setString(1, convExamen.getConvocatoria());
            statement.setString(2, convExamen.getDescripcion());
            statement.setDate(3, Date.valueOf(convExamen.getFecha()));
            statement.setString(4, convExamen.getCurso());
            statement.setNull(5, java.sql.Types.INTEGER);

            if (statement.executeUpdate() > 0) {
                added = true;
                System.out.println("Data inserted!");
            } else {
                System.out.println("Failed!");
            }
        } catch (SQLException e) {
            System.out.println("Error de SQL");
            e.printStackTrace();
        } finally {
            try {
                DBConnection.getInstance().closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return added;
    }

    @Override
    public boolean crearUnidad(UnidadDidactica uniDi) {
        boolean added = false;
        try {
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(INSERTunidadDidactica);
            statement.setString(1, uniDi.getAcronimo());
            statement.setString(2, uniDi.getTitulo());
            statement.setString(3, uniDi.getEvaluacion());
            statement.setString(4, uniDi.getDescripcion());
            if (statement.executeUpdate() > 0) {
                added = true;
                System.out.println("Data inserted into UnidadDidactica!");
            } else {
                System.out.println("Failed to insert into UnidadDidactica!");
            }
        } catch (SQLException e) {
            System.out.println("Error de SQL");
            e.printStackTrace();
        } finally {
            try {
                DBConnection.getInstance().closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return added;
    }

    public void mostrarConvocatorias() {
        try {
            connection = DBConnection.getInstance().getConnection();

            System.out.println("Convocatorias disponibles:");
            while (resultSet.next()) {
                String convocatoria = resultSet.getString("convocatoria");
                System.out.println(convocatoria);
            }
            statement = connection.prepareStatement(GETconvocatorias);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al consultar las convocatorias.");
            e.printStackTrace();
        } finally {
            try {
                DBConnection.getInstance().closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean crearEnunciado(Enunciado enu
    ) {
        boolean added = false;
        try {
            connection = DBConnection.getInstance().getConnection();
            // Insertar el enunciado en la base de datos
            statement = connection.prepareStatement(INSERTENUNCIADO);
            statement.setString(1, enu.getDescripcion());
            statement.setString(2, enu.getNivel().name()); // Dificultad
            statement.setBoolean(3, enu.isDisponible());
            statement.setString(4, enu.getRuta());
            statement.executeUpdate();

            System.out.println("Enunciado insertado correctamente!");

        } catch (SQLException e) {
            System.out.println("Error de SQL");
            e.printStackTrace();
        } finally {
            try {
                DBConnection.getInstance().closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return added;
    }

    @Override
    public ArrayList<Enunciado> consultarEnunciadosPorUnidad(String unidAcronim) {
        ArrayList<Enunciado> enunciadosArray = new ArrayList<>();
        try {
            // Preparamos la consulta SQL
            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(RETURN_ENUNCIADOS_UNIDAD);
            statement.setString(1, unidAcronim);

            // Ejecutamos la consulta
            resultSet = statement.executeQuery();

            // Procesamos los resultados
            while (resultSet.next()) {
                Enunciado enunciadoResult = new Enunciado();
                enunciadoResult.setId(resultSet.getInt("id"));
                enunciadoResult.setDescripcion(resultSet.getString("descripcion"));
                // Comprobar que "nivel" existe en la consulta
                String nivelStr = resultSet.getString("nivel");
                if (nivelStr != null) {
                    Dificultad nivelEnum = Dificultad.valueOf(nivelStr.toUpperCase());
                    enunciadoResult.setNivel(nivelEnum);
                }
                boolean disponible = resultSet.getBoolean("disponible");
                enunciadoResult.setDisponible(disponible);
                enunciadoResult.setRuta(resultSet.getString("ruta"));

                enunciadosArray.add(enunciadoResult);
            }
        } catch (SQLException e) {
            System.out.println("Error de SQL");
            e.printStackTrace();
        } finally {
            try {
                DBConnection.getInstance().closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return enunciadosArray;

    }

    @Override
    public void consultarConvocatoriasConEnunciado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Integer> getEnunciadosIDList() {
        ArrayList<Integer> enunciadosIDList = new ArrayList();

        try {

            connection = DBConnection.getInstance().getConnection();

            statement = connection.prepareStatement(GETAllENUNCIADOSIDS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                enunciadosIDList.add(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBConnection.getInstance().closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return enunciadosIDList;
    }

    @Override
    public String getFilePathFromDatabase(int id
    ) {
        String path = null;
        try {

            connection = DBConnection.getInstance().getConnection();
            statement = connection.prepareStatement(GETFILEPATH);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                path = rs.getString("ruta");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } finally {
            try {
                DBConnection.getInstance().closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return path;
    }

    @Override
    public void asignarEnunciadoConvocatoria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
