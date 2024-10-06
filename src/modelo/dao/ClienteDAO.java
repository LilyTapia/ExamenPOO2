package modelo.dao;

import db.Conexion;
import modelo.entidades.Cliente;
import java.sql.*;

public class ClienteDAO {

    // Método para verificar si un RUT existe
    public boolean rutExiste(String rut) throws SQLException {
        Connection cnx = null;
        boolean existe = false;

        try {
            cnx = Conexion.getInstance().getConnection();
            String query = "SELECT COUNT(*) FROM cliente WHERE rut = ?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, rut);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new SQLException("Error al verificar el RUT: " + e.getMessage());
        }

        return existe;
    }

    // Método para insertar un nuevo cliente
    public void insertarCliente(Cliente cliente) throws SQLException {
        if (rutExiste(cliente.getRut())) {
            throw new SQLException("El RUT ya está registrado en la base de datos.");
        }

        Connection cnx = null;

        try {
            cnx = Conexion.getInstance().getConnection();
            String query = "INSERT INTO cliente (rut, nombre_completo, direccion, comuna, email, telefono) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, cliente.getRut());
            stmt.setString(2, cliente.getNombreCompleto());
            stmt.setString(3, cliente.getDireccion());
            stmt.setString(4, cliente.getComuna());
            stmt.setString(5, cliente.getEmail());
            stmt.setInt(6, cliente.getTelefono());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al insertar cliente: " + e.getMessage());
        }
    }

    // Método para obtener un cliente por RUT
    public Cliente getClientePorRut(String rut) throws SQLException {
        Connection cnx = null;
        Cliente cliente = null;

        try {
            cnx = Conexion.getInstance().getConnection();
            String query = "SELECT * FROM cliente WHERE rut = ?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, rut);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nombreCompleto = rs.getString("nombre_completo");
                String direccion = rs.getString("direccion");
                String comuna = rs.getString("comuna");
                String email = rs.getString("email");
                int telefono = rs.getInt("telefono");
                cliente = new Cliente(rut, nombreCompleto, direccion, comuna, email, telefono);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener cliente: " + e.getMessage());
        }

        return cliente;
    }

    public void actualizarCliente(Cliente cliente) throws SQLException {
        Connection cnx = null;

        try {
            cnx = Conexion.getInstance().getConnection();
            String query = "UPDATE cliente SET nombre_completo = ?, direccion = ?, comuna = ?, email = ?, telefono = ? WHERE rut = ?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, cliente.getNombreCompleto());
            stmt.setString(2, cliente.getDireccion());
            stmt.setString(3, cliente.getComuna());
            stmt.setString(4, cliente.getEmail());
            stmt.setInt(5, cliente.getTelefono());
            stmt.setString(6, cliente.getRut());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar cliente: " + e.getMessage());
        }
    }

}
