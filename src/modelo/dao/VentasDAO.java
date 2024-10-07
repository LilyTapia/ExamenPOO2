package modelo.dao;

import db.Conexion;
import modelo.entidades.Cliente;
import modelo.entidades.Equipo;
import modelo.entidades.Venta;
import modelo.entidades.Desktop;
import modelo.entidades.Laptop;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VentasDAO {

    private ClienteDAO clienteDAO;
    private EquipoDAO equipoDAO;

    public VentasDAO() {
        this.clienteDAO = new ClienteDAO();
        this.equipoDAO = new EquipoDAO();
    }

    // Método para insertar una venta
    public void insertarVenta(Venta venta) throws SQLException {
        String query = "INSERT INTO venta (cliente_rut, equipo_id, fecha_hora) VALUES (?, ?, ?)";

        try (Connection cnx = Conexion.getInstance().getConnection();
             PreparedStatement stmt = cnx.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, venta.getCliente().getRut());
            stmt.setInt(2, venta.getEquipo().getId());
            stmt.setTimestamp(3, new Timestamp(venta.getFechaHora().getTime()));
            stmt.executeUpdate();

            // Obtener el ID generado
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    venta.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al insertar venta: " + e.getMessage(), e);
        }
    }

    // Método para listar todas las ventas
    public List<Venta> listarVentas() throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        String query = "SELECT v.id, v.fecha_hora, c.rut, c.nombre_completo, c.direccion, c.comuna, c.email, c.telefono, "
                     + "e.id AS equipo_id, e.descripcion_modelo, e.cpu, e.disco_duro_mb, e.ram_gb, e.precio, e.tipo, "
                     + "d.potencia_fuente, d.factor_forma, l.tamano_pantalla, l.touch, l.cantidad_puertos_usb "
                     + "FROM venta v "
                     + "JOIN cliente c ON v.cliente_rut = c.rut "
                     + "JOIN equipo e ON v.equipo_id = e.id "
                     + "LEFT JOIN desktop d ON e.id = d.id "
                     + "LEFT JOIN laptop l ON e.id = l.id";

        try (Connection cnx = Conexion.getInstance().getConnection();
             PreparedStatement stmt = cnx.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ventas.add(parseVenta(rs));
            }
        } catch (SQLException e) {
            throw new SQLException("Error al listar ventas: " + e.getMessage(), e);
        }

        return ventas;
    }

    // Método para listar ventas por tipo de equipo
    public List<Venta> listarVentasPorTipo(String tipoEquipo) throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        String query = "SELECT v.id, v.fecha_hora, c.rut, c.nombre_completo, c.direccion, c.comuna, c.email, c.telefono, "
                     + "e.id AS equipo_id, e.descripcion_modelo, e.cpu, e.disco_duro_mb, e.ram_gb, e.precio, e.tipo, "
                     + "d.potencia_fuente, d.factor_forma, l.tamano_pantalla, l.touch, l.cantidad_puertos_usb "
                     + "FROM venta v "
                     + "JOIN cliente c ON v.cliente_rut = c.rut "
                     + "JOIN equipo e ON v.equipo_id = e.id "
                     + "LEFT JOIN desktop d ON e.id = d.id "
                     + "LEFT JOIN laptop l ON e.id = l.id "
                     + "WHERE e.tipo = ?";

        try (Connection cnx = Conexion.getInstance().getConnection();
             PreparedStatement stmt = cnx.prepareStatement(query)) {

            stmt.setString(1, tipoEquipo);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ventas.add(parseVenta(rs));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al listar ventas por tipo: " + e.getMessage(), e);
        }

        return ventas;
    }

    // Parse a single venta from ResultSet
    private Venta parseVenta(ResultSet rs) throws SQLException {
        // Venta
        int ventaId = rs.getInt("id");
        Date fechaHora = new Date(rs.getTimestamp("fecha_hora").getTime());

        // Cliente
        String rut = rs.getString("rut");
        String nombreCompleto = rs.getString("nombre_completo");
        String direccion = rs.getString("direccion");
        String comuna = rs.getString("comuna");
        String email = rs.getString("email");
        String telefono = rs.getString("telefono");
        Cliente cliente = new Cliente(rut, nombreCompleto, direccion, comuna, email, telefono);

        // Equipo
        int equipoId = rs.getInt("equipo_id");
        String descripcionModelo = rs.getString("descripcion_modelo");
        String cpu = rs.getString("cpu");
        int discoDuroMB = rs.getInt("disco_duro_mb");
        int ramGB = rs.getInt("ram_gb");
        double precio = rs.getDouble("precio");
        String tipo = rs.getString("tipo");

        Equipo equipo;
        if (tipo.equalsIgnoreCase("Desktop")) {
            String potenciaFuente = rs.getString("potencia_fuente");
            String factorForma = rs.getString("factor_forma");
            equipo = new Desktop(equipoId, descripcionModelo, cpu, discoDuroMB, ramGB, precio, potenciaFuente, factorForma);
        } else {
            double tamanoPantalla = rs.getDouble("tamano_pantalla");
            boolean touch = rs.getBoolean("touch");
            int cantidadPuertosUSB = rs.getInt("cantidad_puertos_usb");
            equipo = new Laptop(equipoId, descripcionModelo, cpu, discoDuroMB, ramGB, precio, tamanoPantalla, touch, cantidadPuertosUSB);
        }

        return new Venta(ventaId, cliente, equipo, fechaHora);
    }
}
