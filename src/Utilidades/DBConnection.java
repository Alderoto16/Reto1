/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author Omar
 */
public class DBConnection {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/examendb?serverTimezone=Europe/Madrid&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "abcd*1234";

    private static DBConnection instance;
    private Connection connection;

    private DBConnection() throws SQLException {

         connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            Logger.getLogger("this").severe("Connection opened");
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null) {

            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            connection = null;
            instance = null; // Allow recreating the instance if needed
        }
    }
}

