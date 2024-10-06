package controlador;

import modelo.dao.ClienteDAO;
import modelo.entidades.Cliente;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ControladorCliente {

    private ClienteDAO clienteDAO;

    public ControladorCliente() {
        this.clienteDAO = new ClienteDAO();
    }

    // Método para registrar un nuevo cliente
    public void registrarCliente(String rut, String nombreCompleto, String direccion, String comuna, String email, int telefono) {
        try {
            Cliente cliente = new Cliente(rut, nombreCompleto, direccion, comuna, email, telefono);
            clienteDAO.insertarCliente(cliente);
            JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente.");
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error al registrar cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
          
        }
    }

    // Método para verificar si un RUT existe
    public boolean verificarRut(String rut) {
        try {
            return clienteDAO.rutExiste(rut);
        } catch (SQLException e) {
            System.err.println("Error al verificar RUT: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener un Cliente por RUT
    public Cliente obtenerClientePorRut(String rut) {
        try {
            return clienteDAO.getClientePorRut(rut);
        } catch (SQLException e) {
            System.err.println("Error al obtener cliente: " + e.getMessage());
            return null;
        }
    }

  
}
