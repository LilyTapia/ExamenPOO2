package controlador;

import modelo.dao.EquipoDAO;
import modelo.entidades.Desktop;
import modelo.entidades.Equipo;
import modelo.entidades.Laptop;

import java.sql.SQLException;
import java.util.List;

public class ControladorEquipo {
    private EquipoDAO equipoDAO;

    public ControladorEquipo() {
        this.equipoDAO = new EquipoDAO();
    }

    // Método para registrar un nuevo Desktop
    public void registrarDesktop(Desktop desktop) {
        try {
            equipoDAO.insertarEquipo(desktop);
            System.out.println("Desktop registrado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al registrar Desktop: " + e.getMessage());
        }
    }

    // Método para registrar una nueva Laptop
    public void registrarLaptop(Laptop laptop) {
        try {
            equipoDAO.insertarEquipo(laptop);
            System.out.println("Laptop registrada exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al registrar Laptop: " + e.getMessage());
        }
    }

    // Método para listar todos los equipos
    public List<Equipo> listarEquipos() {
        try {
            return equipoDAO.listarEquipos();
        } catch (SQLException e) {
            System.err.println("Error al listar los equipos: " + e.getMessage());
            return null;  // Handle returning an empty list or null
        }
    }

    // Método para buscar un equipo por ID
    public Equipo buscarEquipoPorId(int id) {
        try {
            return equipoDAO.buscarEquipoPorId(id);
        } catch (SQLException e) {
            System.err.println("Error al buscar equipo por ID: " + e.getMessage());
            return null;  // Handle returning null if not found
        }
    }

    // Método para eliminar un equipo por ID
    public void eliminarEquipo(int id) {
        try {
            equipoDAO.eliminarEquipo(id);
            System.out.println("Equipo eliminado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar el equipo: " + e.getMessage());
        }
    }

    // Método para actualizar un equipo
    public void actualizarEquipo(Equipo equipo) {
        try {
            equipoDAO.actualizarEquipo(equipo);
            System.out.println("Equipo actualizado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar el equipo: " + e.getMessage());
        }
    }
}
