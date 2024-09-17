package Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A utility class for managing MySQL database connections.
 * @author 2dami G1
 * @version 1.0
 */
public class MySqlConnection {

    /**
     * The JDBC URL for connecting to the MySQL database.
     */
    private static final String DB_URL = "jdbc:mysql://localhost:3306/examendb?serverTimezone=Europe/Madrid&allowPublicKeyRetrieval=true&useSSL=false";

    /**
     * The singleton instance of the MySqlConnection class.
     */
    private static MySqlConnection instance;

    /**
     * The database connection.
     */
    private Connection connection;

    /**
     * The type of user accessing the database.
     */
    private String userType;

    /**
     * Constructs a MySqlConnection instance with the specified user type.
     *
     * @param userType The type of user accessing the database.
     * @throws SQLException If a database access error occurs.
     */
    private MySqlConnection(String userType) throws SQLException {
        this.userType = userType;
        this.connection = DriverManager.getConnection(DB_URL, userType, userType);
    }

    /**
     * Returns the singleton instance of the MySqlConnection class.
     *
     * @param userType The type of user accessing the database.
     * @return The MySqlConnection instance.
     * @throws SQLException If a database access error occurs.
     */
    public static MySqlConnection getInstance(String userType) throws SQLException {
        if (instance == null || instance.isClosed() || !instance.getUserType().equals(userType)) {
            synchronized (MySqlConnection.class) {
                if (instance == null || instance.isClosed() || !instance.getUserType().equals(userType)) {
                    instance = new MySqlConnection(userType);
                }
            }
        }
        return instance;
    }

    /**
     * Returns the database connection.
     *
     * @return The database connection.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Returns the type of user accessing the database.
     *
     * @return The type of user.
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Checks if the database connection is closed.
     *
     * @return True if the connection is closed, false otherwise.
     * @throws SQLException If a database access error occurs.
     */
    private boolean isClosed() throws SQLException {
        return connection == null || connection.isClosed();
    }

    /**
     * Closes the database connection.
     *
     * @throws SQLException If a database access error occurs.
     */
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            connection = null;
            instance = null; // Allow recreating the instance if needed
        }
    }
}