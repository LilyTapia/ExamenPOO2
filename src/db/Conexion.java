package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Conexion instancia;
    private Connection connection;

    // Database connection parameters
    private static final String URL = "jdbc:mysql://localhost:3306/VENTASCOMPUTEC";
    private static final String USER = "root";
    private static final String PASSWORD = "Rol5346886.";

    private Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the MySQL JDBC driver
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver no encontrado: " + ex.getMessage());
        } catch (SQLException ex) {
            System.err.println("Error al conectar a la base de datos: " + ex.getMessage());
        }
    }

    // Singleton pattern to ensure only one instance of Conexion
    public static Conexion getInstance() {
        if (instancia == null) {
            instancia = new Conexion();
        } else {
            try {
                if (instancia.getConnection() == null || instancia.getConnection().isClosed()) {
                    instancia = new Conexion(); // Reinitialize if the connection is closed or null
                }
            } catch (SQLException e) {
                System.err.println("Error al verificar la conexión: " + e.getMessage());
            }
        }
        return instancia;
    }

    // Get the connection object
    public Connection getConnection() {
        return connection;
    }

    // Method to close the database connection
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
