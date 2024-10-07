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
        String query = getInsertQuery(equipo);

        try (Connection cnx = Conexion.getInstance().getConnection();
             PreparedStatement stmt = cnx.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            setCommonParameters(stmt, equipo);

            if (equipo instanceof Desktop) {
                setDesktopParameters(stmt, (Desktop) equipo);
            } else if (equipo instanceof Laptop) {
                setLaptopParameters(stmt, (Laptop) equipo);
            } else {
                throw new SQLException("Tipo de equipo no soportado.");
            }

            stmt.executeUpdate();

            // Obtener el ID generado
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    equipo.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al insertar equipo: " + e.getMessage(), e);
        }
    }

    // Método para listar todos los equipos
    public List<Equipo> listarEquipos() throws SQLException {
        List<Equipo> equipos = new ArrayList<>();
        String query = "SELECT * FROM equipo";

        try (Connection cnx = Conexion.getInstance().getConnection();
             Statement stmt = cnx.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                equipos.add(parseEquipo(rs));
            }
        } catch (SQLException e) {
            throw new SQLException("Error al listar equipos: " + e.getMessage(), e);
        }

        return equipos;
    }

    // Método para buscar equipo por ID
    public Equipo buscarEquipoPorId(int id) throws SQLException {
        String query = "SELECT * FROM equipo WHERE id = ?";
        try (Connection cnx = Conexion.getInstance().getConnection();
             PreparedStatement stmt = cnx.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return parseEquipo(rs);
                } else {
                    throw new SQLException("No se encontró equipo con el ID: " + id);
                }
            }
        }
    }

    // Método para actualizar un equipo
    public void actualizarEquipo(Equipo equipo) throws SQLException {
        String query = getUpdateQuery(equipo);

        try (Connection cnx = Conexion.getInstance().getConnection();
             PreparedStatement stmt = cnx.prepareStatement(query)) {

            setCommonParameters(stmt, equipo);

            if (equipo instanceof Desktop) {
                setDesktopParameters(stmt, (Desktop) equipo);
            } else if (equipo instanceof Laptop) {
                setLaptopParameters(stmt, (Laptop) equipo);
            }

            stmt.setInt(getUpdateIdParameterIndex(equipo), equipo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar equipo: " + e.getMessage(), e);
        }
    }

    // Método para eliminar un equipo por ID
    public void eliminarEquipo(int id) throws SQLException {
        String query = "DELETE FROM equipo WHERE id = ?";
        try (Connection cnx = Conexion.getInstance().getConnection();
             PreparedStatement stmt = cnx.prepareStatement(query)) {

            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo eliminar equipo con ID: " + id);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar equipo: " + e.getMessage(), e);
        }
    }

    // Utility method to generate the appropriate insert query based on equipo type
    private String getInsertQuery(Equipo equipo) {
        if (equipo instanceof Desktop) {
            return "INSERT INTO equipo (descripcion_modelo, cpu, disco_duro_mb, ram_gb, precio, tipo, potencia_fuente, factor_forma) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        } else if (equipo instanceof Laptop) {
            return "INSERT INTO equipo (descripcion_modelo, cpu, disco_duro_mb, ram_gb, precio, tipo, tamano_pantalla, touch, cantidad_puertos_usb) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        } else {
            return null;
        }
    }

    // Utility method to generate the appropriate update query based on equipo type
    private String getUpdateQuery(Equipo equipo) {
        if (equipo instanceof Desktop) {
            return "UPDATE equipo SET descripcion_modelo = ?, cpu = ?, disco_duro_mb = ?, ram_gb = ?, precio = ?, tipo = ?, potencia_fuente = ?, factor_forma = ? WHERE id = ?";
        } else if (equipo instanceof Laptop) {
            return "UPDATE equipo SET descripcion_modelo = ?, cpu = ?, disco_duro_mb = ?, ram_gb = ?, precio = ?, tipo = ?, tamano_pantalla = ?, touch = ?, cantidad_puertos_usb = ? WHERE id = ?";
        } else {
            return null;
        }
    }

    // Utility method to get the correct ID index for update queries
    private int getUpdateIdParameterIndex(Equipo equipo) {
        return equipo instanceof Desktop ? 9 : 10;
    }

    // Set common parameters for both Laptop and Desktop
    private void setCommonParameters(PreparedStatement stmt, Equipo equipo) throws SQLException {
        stmt.setString(1, equipo.getDescripcionModelo());
        stmt.setString(2, equipo.getCpu());
        stmt.setInt(3, equipo.getDiscoDuroMB());
        stmt.setInt(4, equipo.getRamGB());
        stmt.setDouble(5, equipo.getPrecio());
        stmt.setString(6, equipo.getTipoEquipo());  // This should be implemented in each subclass
    }

    // Set parameters specific to Desktop
    private void setDesktopParameters(PreparedStatement stmt, Desktop desktop) throws SQLException {
        stmt.setString(7, desktop.getPotenciaFuente());
        stmt.setString(8, desktop.getFactorForma());
    }

    // Set parameters specific to Laptop
    private void setLaptopParameters(PreparedStatement stmt, Laptop laptop) throws SQLException {
        stmt.setDouble(7, laptop.getTamanoPantalla());
        stmt.setBoolean(8, laptop.isTouch());
        stmt.setInt(9, laptop.getCantidadPuertosUSB());
    }

    // Parse ResultSet into either Desktop or Laptop
    private Equipo parseEquipo(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String descripcionModelo = rs.getString("descripcion_modelo");
        String cpu = rs.getString("cpu");
        int discoDuroMB = rs.getInt("disco_duro_mb");
        int ramGB = rs.getInt("ram_gb");
        double precio = rs.getDouble("precio");
        String tipo = rs.getString("tipo");

        if ("Desktop".equalsIgnoreCase(tipo)) {
            String potenciaFuente = rs.getString("potencia_fuente");
            String factorForma = rs.getString("factor_forma");
            return new Desktop(id, descripcionModelo, cpu, discoDuroMB, ramGB, precio, potenciaFuente, factorForma);
        } else if ("Laptop".equalsIgnoreCase(tipo)) {
            double tamanoPantalla = rs.getDouble("tamano_pantalla");
            boolean touch = rs.getBoolean("touch");
            int cantidadPuertosUSB = rs.getInt("cantidad_puertos_usb");
            return new Laptop(id, descripcionModelo, cpu, discoDuroMB, ramGB, precio, tamanoPantalla, touch, cantidadPuertosUSB);
        } else {
            throw new SQLException("Tipo de equipo desconocido: " + tipo);
        }
    }
}
