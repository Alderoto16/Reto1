package Controller;

import Utilidades.Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/examendb?serverTimezone=Europe/Madrid";
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
        }

        int menu;
        do {
            showMenu();
            menu = Util.leerInt("¿Que desea hacer?", 0, 7);
            switch (menu) {

                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 0:
                    break;
            }
        } while (menu != 0);
    }

    public static void showMenu() {
        System.out.println("Crear una unidad didáctica (Unidad)");
        System.out.println("Crear una Convocatoria de examen");
        System.out.println("Crear un enunciado de examen agregando las unidades didácticas que va a referir. También\n"
                + "se asociará a este enunciado la convocatoria para la que se crea.");
        System.out.println("Consultar los enunciados de examen en los que se trata una unidad didáctica concreta.");
        System.out.println("Consultar en que convocatorias se ha utilizado un enunciado concreto.");
        System.out.println("Visualizar el documento de texto asociado a un enunciado");
        System.out.println("Asignar un enunciado a una convocatoria.");

    }

}// end of main 
