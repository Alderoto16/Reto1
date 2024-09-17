package Controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Dificultad;
import Models.Enunciado;
import Models.Enunciado.Nivel;
import Models.UnidadDidactica;
import java.sql.DriverManager;
import java.util.ArrayList;

public class Controller implements IController {

    private Connection connection=null;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private CallableStatement callableStatement = null;

    private final String RETURN_ENUNCIADOS_UNIDAD = "SELECT \n" +
"    e.id AS enunciado_id,\n" +
"    e.descripcion AS enunciado_descripcion,\n" +
"    e.nivel AS nivel_dificultad,\n" +
"    e.disponible AS disponible,\n" +
"    e.ruta AS ruta_enunciado,\n" +
"    e.convocatoria_examen AS convocatoria\n" +
"FROM \n" +
"    UnidadDidactica_Enunciado ue\n" +
"JOIN \n" +
"    Enunciado e ON ue.enunciado_id = e.id\n" +
"WHERE \n" +
"    ue.unidad_id = ?";

    @Override
    public void crearUnidad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public  Enunciado consultarEnunciadosPorUnidad() {
        connectionDB();
        ResultSet resultSet = null;
        Enunciado enunuciado = null;
        ArrayList<Enunciado> enunciadosArray = new ArrayList<Enunciado>();
        int idBuscar=0;
        System.out.println("Introduce el ID de la unidad: ");
        idBuscar=Utilidades.Util.leerInt();

        try {

            statement = connection.prepareStatement(RETURN_ENUNCIADOS_UNIDAD);
            statement.setInt(1, idBuscar);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Enunciado enunciado = new Enunciado();

                enunciado.setId(resultSet.getInt("id"));
                enunciado.setDescripcion("descripcion");
                String nivelStr = resultSet.getString("nivel");
                Nivel nivelEnum = Nivel.valueOf(nivelStr.toUpperCase()); 
                enunciado.setNivel(nivelEnum);
                boolean disponible = resultSet.getBoolean("disponible");
                enunciado.setDisponible(disponible);
                enunciado.setRuta(resultSet.getString("ruta"));
                enunciado.setConvocatoriaExamen("convocatoria_examen");

                enunciadosArray.add(enunciado);
            }
        } catch (SQLException e) {
            System.out.println("Error de SQL");
            e.printStackTrace();
        } finally {
            // Cerramos ResultSet
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    System.out.println("Error en cierre del ResultSet");
                }
            }
        }
        for (int i = 0; i < enunciadosArray.size(); i++) {
            
              System.out.println(enunciadosArray.get(i));
            
        }
        return null;
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
    @Override
    public Enunciado consultarEnunciadosPorUnidad(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
