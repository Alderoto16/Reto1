package Controller;

import Models.Dificultad;
import Utilidades.Util;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller implements IController {

    private Connection connection = null;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private CallableStatement callableStatement = null;

    // Queries
    final String INSERTunidadDidactica = "INSERT INTO UnidadDidactica (id, acronimo, titulo, evaluacion, descripcion) VALUES (?, ?, ?, ?, ?)";
    final String INSERTconvocatoria = "INSERT INTO ConvocatoriaExamen (convocatoria, descripcion, fecha, curso, enunciadoID) VALUES (?, ?, ?, ?, ?)";
    final String INSERTenunciado = "INSERT INTO Enunciado (id, descripcion, nivel, disponible, ruta) VALUES (?, ?, ?, ?, ?)";
    final String GETconvocatorias = "SELECT * FROM ConvocatoriaExamen";
    final String GETunidades = "SELECT id FROM UnidadDidactica";
    final String INSERTunidad_id = "INSERT INTO UnidadDidactica_Enunciado (unidad_id, enunciado_id) VALUES (?, ?)";
    final String CHECKIDUNIDAD = "SELECT id FROM UnidadDidactica WHERE id = ?";
    final String CHECKIDCONVOCATORIA = "SELECT convocatoria FROM ConvocatoriaExamen WHERE convocatoria = ?";
    final String CHECKIDENUNCIADO = "SELECT id FROM Enunciado WHERE id = ?";
     final String GETenunciadoPorUnidad =  "SELECT e.id, e.descripcion, e.nivel, e.disponible, e.ruta, e.convocatoria_examen FROM Enunciado e WHERE e.id IN (SELECT enunciado_id FROM UnidadDidactica_Enunciado WHERE unidad_id = (SELECT id FROM UnidadDidactica where id=?))";
    
     
     
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

    private boolean isConvocatoriaExists(String convocatoria) {
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
    }

    private boolean comprobarUnidades() {
        try {
            connectionDB();
            PreparedStatement ps = connection.prepareStatement(GETunidades);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean comprobarConvocatorias() {
        try {
            connectionDB();
            statement = connection.prepareStatement(GETconvocatorias);
            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

 @Override
    public boolean crearUnidad() {
        boolean added = false;
        try {
            connectionDB();
            int id;
            while (true) {
                System.out.println("Introduce el ID de la unidad:");
                id = Util.leerInt();
                if (!isIDExists(CHECKIDUNIDAD, id)) {
                    break;
                }
                System.out.println("El ID de la unidad ya existe. Por favor, introduce un ID único.");
            }
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
                System.out.println("Data inserted into UnidadDidactica!");
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
        try {
            connectionDB();
            String convocatoria;
            while (true) {
                System.out.println("Introduce el id de la convocatoria:");
                convocatoria = Util.introducirCadena();
                if (!isConvocatoriaExists(convocatoria)) {
                    break;
                }
                System.out.println("La convocatoria ya existe. Por favor, introduce un ID único.");
            }
            System.out.println("Introduce el descripcion:");
            String descripcion = Util.introducirCadena();
            System.out.println("Introduce la fecha:");
            LocalDate localDate = Util.leerFechaAMD();
            Date fecha = Date.valueOf(localDate);
            System.out.println("Introduce el curso:");
            String curso = Util.introducirCadena();

            statement = connection.prepareStatement(INSERTconvocatoria);
            statement.setString(1, convocatoria);
            statement.setString(2, descripcion);
            statement.setDate(3, fecha);
            statement.setString(4, curso);
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
        }
        return added;
    }
    
     public void mostrarConvocatorias() {
        try {
            connectionDB();
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
        }
    }

@Override
public boolean crearEnunciado() {
    boolean added = false;
    try {
        connectionDB();

        // Verificar si hay unidades o convocatorias disponibles
        if (!comprobarUnidades() || !comprobarConvocatorias()) {
            System.out.println("No se puede crear el enunciado porque no hay unidades o convocatorias disponibles.");
            return false;
        }

        // Solicitar un ID único para el enunciado
        int id;
        while (true) {
            System.out.println("Introduce el id del enunciado: ");
            id = Util.leerInt();
            if (!isEnunciadoExists(id)) {
                break;
            }
            System.out.println("El ID del enunciado ya existe. Por favor, introduce un ID único.");
        }

        // Solicitar los datos del enunciado
        System.out.println("Introduce la descripcion del enunciado: ");
        String descripcion = Util.introducirCadena();

        // Nivel de dificultad: 'ALTO', 'MEDIO', 'BAJO'
        System.out.println("Introduce el nivel del enunciado (ALTO, MEDIO, BAJO): ");
        String nivel = Util.introducirCadena().toUpperCase();
        if (!nivel.equals("ALTO") && !nivel.equals("MEDIO") && !nivel.equals("BAJO")) {
            System.out.println("Nivel inválido. Debe ser ALTO, MEDIO o BAJO.");
            return false;
        }

        // Disponibilidad del enunciado (true/false)
        System.out.println("¿Está disponible el enunciado? (true/false): ");
        boolean disponible = Util.leerBooleano();

        // Ruta del archivo o documento
        System.out.println("Introduce la ruta del enunciado: ");
        String ruta = Util.introducirCadena();

        // Insertar el enunciado en la base de datos
        statement = connection.prepareStatement(INSERTenunciado);
        statement.setInt(1, id);
        statement.setString(2, descripcion);
        statement.setString(3, nivel); // Dificultad
        statement.setBoolean(4, disponible);
        statement.setString(5, ruta);
        statement.executeUpdate();

        System.out.println("Enunciado insertado correctamente!");

        // Seleccionar convocatoria para asociar con el enunciado
        mostrarConvocatorias(); // Mostrar las convocatorias de nuevo
        String convocatoriaExamen;
        while (true) {
            System.out.println("Introduce la convocatoria del examen: ");
            convocatoriaExamen = Util.introducirCadena();
            if (isConvocatoriaExists(convocatoriaExamen)) {
                break;
            }
            System.out.println("La convocatoria no existe. Por favor, introduce una convocatoria válida.");
        }

        // Actualizar la convocatoria con el ID del enunciado recién creado
        PreparedStatement updateConvocatoriaStmt = connection.prepareStatement("UPDATE ConvocatoriaExamen SET enunciadoID = ? WHERE convocatoria = ?");
        updateConvocatoriaStmt.setInt(1, id); // Usar el id del enunciado
        updateConvocatoriaStmt.setString(2, convocatoriaExamen);
        updateConvocatoriaStmt.executeUpdate();
        System.out.println("Convocatoria actualizada correctamente con el enunciado.");

        // Mostrar unidades didácticas
        System.out.println("Unidades Didácticas disponibles:");
        PreparedStatement mostrarUnidadesStmt = connection.prepareStatement(GETunidades);
        ResultSet unidadesResultSet = mostrarUnidadesStmt.executeQuery();

        List<Integer> unidadIds = new ArrayList<>();
        int totalUnidades = 0;

        while (unidadesResultSet.next()) {
            int unidadId = unidadesResultSet.getInt("id");
            System.out.println("ID: " + unidadId);
            unidadIds.add(unidadId);
            totalUnidades++;
        }

        // Preguntar cuántas unidades didácticas desea introducir
        System.out.println("Cuántas unidades didácticas desea introducir (máximo " + totalUnidades + "): ");
        int cantidadUnidades;
        while (true) {
            cantidadUnidades = Util.leerInt();
            if (cantidadUnidades > 0 && cantidadUnidades <= totalUnidades) {
                break;
            } else {
                System.out.println("Número inválido. Debe ser entre 1 y " + totalUnidades + ".");
            }
        }

        // Vincular el enunciado a las unidades didácticas seleccionadas
        for (int i = 0; i < cantidadUnidades; i++) {
            int unidadId;
            while (true) {
                System.out.println("Introduce el ID de la unidad " + (i + 1) + ":");
                unidadId = Util.leerInt();
                if (unidadIds.contains(unidadId)) {
                    // Si el ID es válido, insertar la relación
                    PreparedStatement insertUnidadIdStmt = connection.prepareStatement(INSERTunidad_id);
                    insertUnidadIdStmt.setInt(1, unidadId);
                    insertUnidadIdStmt.setInt(2, id); // Set enunciado_id

                    if (insertUnidadIdStmt.executeUpdate() > 0) {
                        System.out.println("Enunciado vinculado a la unidad correctamente!");
                    } else {
                        System.out.println("Error al vincular el enunciado con la unidad didáctica.");
                    }
                    break; // Salir del bucle si el ID fue válido y se insertó correctamente
                } else {
                    System.out.println("El ID de la unidad no existe. Intenta de nuevo.");
                }
            }
        }

        added = true;

    } catch (SQLException e) {
        System.out.println("Error de SQL");
        e.printStackTrace();
    }
    return added;
}


     @Override
    public void consultarEnunciadosPorUnidad() {
		try {   
                    connectionDB();
                    System.out.println("Introduce el ID de la unidad:");
                    int id = Util.leerInt();
                          
			statement = connection.prepareStatement(GETenunciadoPorUnidad);
			statement.setInt(1, id);
                        
                          ResultSet rs = statement.executeQuery();
                        
                        while (rs.next()) {
                int enunciadoId = rs.getInt("id");
                String descripcion = rs.getString("descripcion");
                String nivel = rs.getString("nivel");
                boolean disponible = rs.getBoolean("disponible");
                String ruta = rs.getString("ruta");
                String convocatoriaExamen = rs.getString("convocatoria_examen");

                // Imprimimos los resultados
                System.out.println("Enunciado ID: " + enunciadoId);
                System.out.println("Descripción: " + descripcion);
                System.out.println("Nivel: " + nivel);
                System.out.println("Disponible: " + disponible);
                System.out.println("Ruta: " + ruta);
                System.out.println("Convocatoria Examen: " + convocatoriaExamen);
                System.out.println("----------------------------------");
            }
                        
			if (statement.executeUpdate() > 0) {
				System.out.println("Data");
			} else {
				System.out.println("Failed!");
			}
		} catch (SQLException e) {
			System.out.println("Error de SQL");
			e.printStackTrace();
		} 
    }

    @Override
    public void consultarConvocatoriasConEnunciado() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void visualizarTextoEnunciado() {
       
    }

    @Override
    public void asignarEnunciadoConvocatoria() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
