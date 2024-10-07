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

            // Obtener el equipo por ID
            Equipo equipoSeleccionado = equipoDAO.buscarEquipoPorId(idEquipo);
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
        }
    }

    // Método para listar todas las ventas
    public List<Venta> listarVentas() {
        try {
            return ventasDAO.listarVentas();
        } catch (SQLException e) {
            System.err.println("Error al listar las ventas: " + e.getMessage());
            return null;
        }
    }

    // Método para listar ventas por tipo de equipo
    public List<Venta> listarVentasPorTipo(String tipo) {
        try {
            return ventasDAO.listarVentasPorTipo(tipo);
        } catch (SQLException e) {
            System.err.println("Error al listar ventas por tipo: " + e.getMessage());
            return null;
        }
    }

    // Método para listar equipos disponibles
    public List<Equipo> listarEquiposDisponibles() {
        try {
            return equipoDAO.listarEquipos();
        } catch (SQLException e) {
            System.err.println("Error al listar equipos disponibles: " + e.getMessage());
            return null;
        }
    }

    // Otros métodos como actualizar, eliminar ventas pueden agregarse aquí
}
