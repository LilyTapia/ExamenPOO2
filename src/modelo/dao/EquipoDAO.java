package modelo.dao;

import db.Conexion;
import modelo.entidades.Desktop;
import modelo.entidades.Equipo;
import modelo.entidades.Laptop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAO {

    // Método para insertar un equipo (Desktop o Laptop)
    public void insertarEquipo(Equipo equipo) throws SQLException {
        Connection cnx = null;
        String query = "";

        if (equipo instanceof Desktop) {
            query = "INSERT INTO equipo (descripcion_modelo, cpu, disco_duro_mb, ram_gb, precio, tipo, potencia_fuente, factor_forma) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        } else if (equipo instanceof Laptop) {
            query = "INSERT INTO equipo (descripcion_modelo, cpu, disco_duro_mb, ram_gb, precio, tipo, tamano_pantalla, touch, cantidad_puertos_usb) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        } else {
            throw new SQLException("Tipo de equipo no soportado.");
        }

        try {
            cnx = Conexion.getInstance().getConnection();
            PreparedStatement stmt = cnx.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, equipo.getDescripcionModelo());
            stmt.setString(2, equipo.getCpu());
            stmt.setInt(3, equipo.getDiscoDuroMB());
            stmt.setInt(4, equipo.getRamGB());
            stmt.setDouble(5, equipo.getPrecio());

            if (equipo instanceof Desktop) {
                Desktop desktop = (Desktop) equipo;
                stmt.setString(6, desktop.getTipoEquipo());
                stmt.setString(7, desktop.getPotenciaFuente());
                stmt.setString(8, desktop.getFactorForma());
            } else if (equipo instanceof Laptop) {
                Laptop laptop = (Laptop) equipo;
                stmt.setString(6, laptop.getTipoEquipo());
                stmt.setDouble(7, laptop.getTamanoPantalla());
                stmt.setBoolean(8, laptop.isTouch());
                stmt.setInt(9, laptop.getCantidadPuertosUSB());
            }

            stmt.executeUpdate();

            // Obtener el ID generado
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                equipo.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new SQLException("Error al insertar equipo: " + e.getMessage());
        }
    }

    // Método para listar todos los equipos
    public List<Equipo> listarEquipos() throws SQLException {
        List<Equipo> equipos = new ArrayList<>();
        Connection cnx = null;

        String query = "SELECT * FROM equipo";

        try {
            cnx = Conexion.getInstance().getConnection();
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String descripcionModelo = rs.getString("descripcion_modelo");
                String cpu = rs.getString("cpu");
                int discoDuroMB = rs.getInt("disco_duro_mb");
                int ramGB = rs.getInt("ram_gb");
                double precio = rs.getDouble("precio");
                String tipo = rs.getString("tipo");

                if (tipo.equalsIgnoreCase("Desktop")) {
                    String potenciaFuente = rs.getString("potencia_fuente");
                    String factorForma = rs.getString("factor_forma");
                    equipos.add(new Desktop(id, descripcionModelo, cpu, discoDuroMB, ramGB, precio, potenciaFuente, factorForma));
                } else if (tipo.equalsIgnoreCase("Laptop")) {
                    double tamanoPantalla = rs.getDouble("tamano_pantalla");
                    boolean touch = rs.getBoolean("touch");
                    int cantidadPuertosUSB = rs.getInt("cantidad_puertos_usb");
                    equipos.add(new Laptop(id, descripcionModelo, cpu, discoDuroMB, ramGB, precio, tamanoPantalla, touch, cantidadPuertosUSB));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al listar equipos: " + e.getMessage());
        }

        return equipos;
    }

    
}
