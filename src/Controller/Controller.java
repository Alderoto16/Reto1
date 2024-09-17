package Controller;

import Models.Dificultad;
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
import java.time.LocalDate;

public class Controller implements IController {

    private Connection connection = null;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private CallableStatement callableStatement = null;

    //Querys
    final String INSERTunidadDidactica = "INSERT INTO UnidadDidactica (id, acronimo, titulo, evaluacion, descripcion) VALUES (?, ?, ?, ?, ?)";
    final String INSERTconvocatoria = "INSERT INTO ConvocatoriaExamen (convocatoria, descripcion, fecha, curso) VALUES (?, ?, ?, ?)";
    final String INSERTenunciado = "INSERT INTO Enunciado (id, descripcion, nivel, disponible, ruta, convocatoria_examen) VALUES (?, ?, ?, ?, ?, ?)";
    final String GETconvocatorias = "SELECT * FROM ConvocatoriaExamen";
    final String INSERTunidad_id = "INSERT INTO UnidadDidactica_Enunciado (unidad_id, enunciado_id) VALUES (?, 0)";
    final String UPDATEenunciado_id = "UPDATE UnidadDidactica_Enunciado SET enunciado_id WHERE unidad_id = ?";
    
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

            // Insert into UnidadDidactica table
            statement = connection.prepareStatement(INSERTunidadDidactica);
            statement.setInt(1, id);
            statement.setString(2, acronimo);
            statement.setString(3, titulo);
            statement.setString(4, evaluacion);
            statement.setString(5, descripcion);
            if (statement.executeUpdate() > 0) {
                // Insert into UnidadDidactica_Enunciado table
                PreparedStatement insertUnidadIdStmt = connection.prepareStatement(INSERTunidad_id);
                insertUnidadIdStmt.setInt(1, id);
                if (insertUnidadIdStmt.executeUpdate() > 0) {
                    added = true;
                    System.out.println("Data inserted into UnidadDidactica and UnidadDidactica_Enunciado!");
                } else {
                    System.out.println("Failed to insert into UnidadDidactica_Enunciado!");
                }
            } else {
                System.out.println("Failed to insert into UnidadDidactica!");
            }
        } catch (SQLException e) {
            System.out.println("Error de SQL");
            e.printStackTrace();
        }
        return added;
    }


    @Override
    public boolean crearConvocatoria() {
        boolean added = false;
        System.out.println("Introduce el id:");
        String convocatoria = Util.introducirCadena();
        System.out.println("Introduce el descrpicion:");
        String descripcion = Util.introducirCadena();
        System.out.println("Introduce la fecha:");
        LocalDate localDate = Util.leerFechaAMD();
        Date fecha = Date.valueOf(localDate);
        System.out.println("Introduce el curso:");
        String curso = Util.introducirCadena();

        try {
            connectionDB();
            statement = connection.prepareStatement(INSERTconvocatoria);
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

    public void mostrarConvocatorias() {
        try {
            connectionDB();
            statement = connection.prepareStatement(GETconvocatorias);
            resultSet = statement.executeQuery();

            System.out.println("Convocatorias disponibles:");
            while (resultSet.next()) {
                String convocatoria = resultSet.getString("convocatoria");
                System.out.println(convocatoria);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar las convocatorias.");
            e.printStackTrace();
        }
    }

    @Override
    public boolean crearEnunciado() {
        boolean added = false;
        System.out.println("Introduce el id: ");
        int id = Util.leerInt();
        System.out.println("Introduce la descripcion: ");
        String descripcion = Util.introducirCadena();
        Dificultad nivel = Util.stringToEnum();
        System.out.println("Introduce la disponibilidad: ");
        boolean disponible = Util.leerBooleano();
        System.out.println("Introduce la ruta: ");
        String ruta = Util.introducirCadena();
        mostrarConvocatorias();
        String convocatoriaExamen;
        while (true) {
            System.out.println("Introduce la convocatoria del examen: ");
            convocatoriaExamen = Util.introducirCadena();

            // Verificar si la convocatoria existe
            boolean existe = false;
            try {
                statement = connection.prepareStatement(GETconvocatorias);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String convocatoria = resultSet.getString("convocatoria");
                    if (convocatoria.equals(convocatoriaExamen)) {
                        existe = true;
                        break;
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error al verificar la convocatoria.");
                e.printStackTrace();
            }

            if (existe) {
                break;
            } else {
                System.out.println("La convocatoria no existe. Por favor, introduce una convocatoria válida.");
            }
        }

        try {
            connectionDB();
            statement = connection.prepareStatement(INSERTenunciado);
            statement.setInt(1, id);
            statement.setString(2, descripcion);
            statement.setString(3, nivel.name());
            statement.setBoolean(4, disponible);
            statement.setString(5, ruta);
            statement.setString(6, convocatoriaExamen);
            if (statement.executeUpdate() > 0) {
                added = true;
                System.out.println("Data inserted!");
                
                // Update the UnidadDidactica_Enunciado table
                PreparedStatement updateEnunciadoIdStmt = connection.prepareStatement(UPDATEenunciado_id);
                updateEnunciadoIdStmt.setInt(2, id);  // Assuming 'id' corresponds to 'unidad_id'
                updateEnunciadoIdStmt.setInt(1, id);  // Set 'enunciado_id' here as needed
                if (updateEnunciadoIdStmt.executeUpdate() > 0) {
                    System.out.println("Enunciado ID updated!");
                } else {
                    System.out.println("Failed to update Enunciado ID!");
                }
                
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
