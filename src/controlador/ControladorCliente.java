package controlador;

import modelo.dao.ClienteDAO;
import modelo.entidades.Cliente;
import java.sql.SQLException;

public class ControladorCliente {

    private ClienteDAO clienteDAO;

    public ControladorCliente() {
        this.clienteDAO = new ClienteDAO();
    }

    // Método para registrar un nuevo cliente
    public boolean registrarCliente(String rut, String nombreCompleto, String direccion, String comuna, String email, String telefono) throws SQLException {
        // Validate if the RUT already exists
        if (verificarRut(rut)) {
            return false; // Return false if the RUT exists
        }

        Cliente cliente = new Cliente(rut, nombreCompleto, direccion, comuna, email, telefono);
        clienteDAO.insertarCliente(cliente);
        return true; // Registration was successful
    }

    // Método para verificar si un RUT existe
    public boolean verificarRut(String rut) throws SQLException {
        return clienteDAO.rutExiste(rut);
    }

    // Método para obtener un Cliente por RUT
    public Cliente obtenerClientePorRut(String rut) throws SQLException {
        return clienteDAO.getClientePorRut(rut);
    }
}
