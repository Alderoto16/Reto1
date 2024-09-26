package Controller;

import Models.ConvocatoriaExamen;
import Models.Enunciado;
import Models.UnidadDidactica;
import Utilidades.Util;
import dao.DAO;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Controller implements IController {

    DAO dao = new DAO();

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/examendb?serverTimezone=Europe/Madrid&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "abcd*1234";

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    private CallableStatement callableStatement = null;

    // Queries
    final String GETunidades = "SELECT id FROM UnidadDidactica";
    final String INSERTunidad_id = "INSERT INTO UnidadDidactica_Enunciado (unidad_id, enunciado_id) VALUES (?, ?)";
    final String CHECKIDUNIDAD = "SELECT id FROM UnidadDidactica WHERE id = ?";
    final String CHECKIDCONVOCATORIA = "SELECT convocatoria FROM ConvocatoriaExamen WHERE convocatoria = ?";
    final String CHECKIDENUNCIADO = "SELECT id FROM Enunciado WHERE id = ?";
    final String GETenunciadoPorUnidad = "SELECT e.id, e.descripcion, e.nivel, e.disponible, e.ruta, e.convocatoria_examen FROM Enunciado e WHERE e.id IN (SELECT enunciado_id FROM UnidadDidactica_Enunciado WHERE unidad_id = (SELECT id FROM UnidadDidactica where id=?))";
  

    final String rutaEnunciado = "SELECT ruta FROM Enunciado WHERE id = ?";
    private static final String DOCS_DIR = "src/enunciados/";

    private boolean isIDExists(String query, int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    private boolean comprobarUnidades() {
        try {
            // connectionDB();
            PreparedStatement ps = connection.prepareStatement(GETunidades);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /*
    private boolean comprobarConvocatorias() {
        try {
            //connectionDB();
            statement = connection.prepareStatement(GETconvocatorias);
            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    */
    
    private boolean isUnidadEnunciadoExists(int unidadId, int enunciadoId) {
        boolean exists = false;
        String query = "SELECT COUNT(*) FROM UnidadDidactica_Enunciado WHERE unidad_id = ? AND enunciado_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, unidadId);
            stmt.setInt(2, enunciadoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                exists = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar la relación UnidadDidactica-Enunciado.");
            e.printStackTrace();
        }
        return exists;
    }

    @Override
    public void asignarEnunciadoConvocatoria() {
        try {
            // connectionDB();

            // --> Paso 1: Obtener convocatorias con enunciado NULL
            // --> Paso 2: Elegir una convocatoria
            // --> Paso 3: Mostrar todos los enunciados disponibles
            // --> Paso 4: Elegir un enunciado
            
            // Actualizar la convocatoria seleccionada con el enunciado
            
            System.out.println("Convocatorias con enunciado NULL:");
            PreparedStatement ps = connection.prepareStatement("SELECT convocatoria FROM ConvocatoriaExamen WHERE enunciadoID IS NULL");
            ResultSet rs = ps.executeQuery();

            // Almacenar las convocatorias que tienen enunciado NULL
            List<String> convocatoriasNull = new ArrayList<>();
            while (rs.next()) {
                String convocatoria = rs.getString("convocatoria");
                convocatoriasNull.add(convocatoria);
                System.out.println(convocatoria);
            }

            // Verificar si hay convocatorias disponibles
            if (convocatoriasNull.isEmpty()) {
                System.out.println("No hay convocatorias con enunciado NULL.");
                return;
            }

            // Paso 2: Elegir una convocatoria
            System.out.println("Selecciona una convocatoria de la lista anterior:");
            String convocatoriaSeleccionada = Util.introducirCadena();
            if (!convocatoriasNull.contains(convocatoriaSeleccionada)) {
                System.out.println("Convocatoria no válida. Saliendo.");
                return;
            }

            // Paso 3: Mostrar todos los enunciados disponibles
            System.out.println("Enunciados disponibles:");
            PreparedStatement psEnunciados = connection.prepareStatement("SELECT id, descripcion FROM Enunciado");
            ResultSet rsEnunciados = psEnunciados.executeQuery();

            // Almacenar enunciados
            List<Integer> enunciadosDisponibles = new ArrayList<>();
            while (rsEnunciados.next()) {
                int id = rsEnunciados.getInt("id");
                String descripcion = rsEnunciados.getString("descripcion");
                enunciadosDisponibles.add(id);
                System.out.println("ID: " + id + ", Descripción: " + descripcion);
            }

            // Verificar si hay enunciados disponibles
            if (enunciadosDisponibles.isEmpty()) {
                System.out.println("No hay enunciados disponibles.");
                return;
            }

            // Paso 4: Elegir un enunciado
            System.out.println("Introduce el ID del enunciado que deseas asignar:");
            int enunciadoSeleccionado = Util.leerInt();
            if (!enunciadosDisponibles.contains(enunciadoSeleccionado)) {
                System.out.println("Enunciado no válido. Saliendo.");
                return;
            }

            // Actualizar la convocatoria seleccionada con el enunciado
            PreparedStatement updateStmt = connection.prepareStatement("UPDATE ConvocatoriaExamen SET enunciadoID = ? WHERE convocatoria = ?");
            updateStmt.setInt(1, enunciadoSeleccionado);
            updateStmt.setString(2, convocatoriaSeleccionada);

            int rowsUpdated = updateStmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Enunciado asignado a la convocatoria correctamente.");
            } else {
                System.out.println("Error al asignar el enunciado a la convocatoria.");
            }

        } catch (SQLException e) {
            System.out.println("Error de SQL");
            e.printStackTrace();
        }
    }

    //in TOP should be completed 
    // this func should go to DAO too 
    public boolean isConvocatoriaExists(String convocatoria) {
        try {
            PreparedStatement ps = connection.prepareStatement(CHECKIDCONVOCATORIA);
            ps.setString(1, convocatoria);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override
    public ArrayList<Enunciado> consultarEnunciadosPorUnidad(String unidadAcronim) {
        return dao.consultarEnunciadosPorUnidad(unidadAcronim);
    }

    @Override
    public boolean crearConvocatoria(ConvocatoriaExamen convExamen) {
        return dao.crearConvocatoria(convExamen);
    }

    @Override
    public boolean crearUnidad(UnidadDidactica uniDi) {
        return dao.crearUnidad(uniDi);
    }

    public void mostrarConvocatorias() {
        dao.mostrarConvocatorias();
    }

    @Override
    public void consultarConvocatoriasConEnunciado() {

    }

    @Override
    public boolean crearEnunciado(Enunciado enu) {
        return dao.crearEnunciado(enu);
    }

    public ArrayList<Integer> getEnunciadosIDList() {
        return dao.getEnunciadosIDList();
    }

    @Override
    public void visualizarTextoEnunciado(int enunciadoId) {
        String filePath = dao.getFilePathFromDatabase(enunciadoId);
        openFile(filePath);
    }

    public void createEnunciadosFolder() {
        File docsDir = new File(DOCS_DIR);
        if (!docsDir.exists()) {
            docsDir.mkdirs();  // Create the directory if it doesn't exist
        }
    }

    // create file of enunciado
    public String createFile(String docName) {
        // Ensure "docs" folder exists
        createEnunciadosFolder();
        // Define the relative path of the file inside "docs"
        String relativePath = DOCS_DIR + docName + ".docx";
        File file = new File(relativePath);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                //saveFilePathToDatabase(relativePath);
                return relativePath;
            } else {
                System.out.println("File already exists.");
                return relativePath;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }
        return null;
    }

    public static void openFile(String relativePath) {
        File file = new File(relativePath);
        if (file.exists()) {
            System.out.println("Opening file: " + file.getAbsolutePath());
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(file);  // This will open the file with its default program (e.g., Microsoft Word)
                } else {
                    System.out.println("Desktop is not supported on this platform.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while opening the file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("File not found.");
        }
    }

    /*
    public void connectionDB() {
        String url = "jdbc:mysql://localhost:3306/examendb?serverTimezone=Europe/Madrid";
        String user = "root";
        String password = "abcd*1234";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el controlador JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos.");
            e.printStackTrace();
        }
    }
     */
 /*
    private boolean isEnunciadoExists(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(CHECKIDENUNCIADO);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }*/
    // open connection 
    /*private void openConnection() {
        try {

            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            Logger.getLogger("this").severe("Connection opened");

        } catch (SQLException e) {
            Logger.getLogger("this").severe(e.getLocalizedMessage());

        }
    }

    // close connection 
    private void closeConnection() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            Logger.getLogger("this").info("Connection closed");
        } catch (SQLException e) {
            Logger.getLogger("this").info(e.getLocalizedMessage());

        }
    }*/
    // create package or directory if does not existe 
    //to get file from db
    /*
    public String getFilePathFromDatabase(int id) {
        String path = null;
        try {
            String sql = "SELECT ruta FROM Enunciado WHERE id = ?";
            this.openConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                path = rs.getString("ruta");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return path;
    }*/
    // Method to open a file using the Microsoft Office application
    // ge list of enunciados id that exit in our DB
    /*public ArrayList<Integer> getEnunciadosIDList() {
        ArrayList<Integer> enunciadosIDList = new ArrayList();
        try {
            String sql = "SELECT id FROM Enunciado";
            this.openConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                enunciadosIDList.add(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return enunciadosIDList;
    }*/
}// class
