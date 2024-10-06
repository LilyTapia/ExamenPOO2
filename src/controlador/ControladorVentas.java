package controlador;

import modelo.dao.ClienteDAO;
import modelo.dao.EquipoDAO;
import modelo.dao.VentasDAO;
import modelo.entidades.Cliente;
import modelo.entidades.Equipo;
import modelo.entidades.Venta;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ControladorVentas {
    private VentasDAO ventasDAO;
    private ClienteDAO clienteDAO;
    private EquipoDAO equipoDAO;

    public ControladorVentas() {
        this.ventasDAO = new VentasDAO();
        this.clienteDAO = new ClienteDAO();
        this.equipoDAO = new EquipoDAO();
    }

    // Método para registrar una nueva venta
    public void registrarVenta(String rutCliente, int idEquipo) {
        try {
            // Verificar si el cliente existe
            if (!clienteDAO.rutExiste(rutCliente)) {
                System.err.println("El cliente con RUT " + rutCliente + " no existe.");
                return;
            }

            // Obtener el cliente
            Cliente cliente = clienteDAO.getClientePorRut(rutCliente);
            if (cliente == null) {
                System.err.println("Error al obtener datos del cliente.");
                return;
            }

            // Obtener el equipo
            List<Equipo> equipos = equipoDAO.listarEquipos();
            Equipo equipoSeleccionado = null;
            for (Equipo eq : equipos) {
                if (eq.getId() == idEquipo) {
                    equipoSeleccionado = eq;
                    break;
                }
            }

            if (equipoSeleccionado == null) {
                System.err.println("El equipo con ID " + idEquipo + " no existe.");
                return;
            }

            // Crear la venta
            Venta venta = new Venta(0, cliente, equipoSeleccionado, new Date());
            ventasDAO.insertarVenta(venta);
            System.out.println("Venta registrada exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al registrar venta: " + e.getMessage());
            // Aquí podrías manejar la excepción de manera más sofisticada, como mostrar un diálogo en la vista
        }
    }

    // Método para listar todas las ventas
    public List<Venta> listarVentas() throws SQLException {
        return ventasDAO.listarVentas();
    }

    // Método para listar ventas por tipo de equipo
    public List<Venta> listarVentasPorTipo(String tipo) throws SQLException {
        // Implementa este método en VentasDAO para filtrar las ventas según el tipo de equipo
        return ventasDAO.listarVentasPorTipo(tipo);
    }

    // Método para listar equipos disponibles
    public List<Equipo> listarEquiposDisponibles() throws SQLException {
        return equipoDAO.listarEquipos();
    }

    // Otros métodos como actualizar, eliminar ventas pueden agregarse aquí
}
