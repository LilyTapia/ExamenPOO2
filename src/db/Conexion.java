package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Conexion instancia;
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String USER = "root";
    private static final String PASSWORD = "Rol5346886.";

    private Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa");
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver no encontrado: " + ex.getMessage());
        } catch (SQLException ex) {
            System.err.println("Error al conectar a la base de datos: " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Conexion getInstance() {
        if (instancia == null) {
            instancia = new Conexion();
        } else {
            try {
                if (instancia.getConnection() == null || instancia.getConnection().isClosed()) {
                    instancia = new Conexion();
                }
            } catch (SQLException e) {
                System.err.println("Error al verificar la conexión: " + e.getMessage());
            }
        }
        return instancia;
    }
}
