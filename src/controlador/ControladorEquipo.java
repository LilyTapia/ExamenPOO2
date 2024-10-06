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
            // Aquí podrías manejar la excepción de manera más sofisticada, como mostrar un diálogo en la vista
        }
    }

    // Método para registrar una nueva Laptop
    public void registrarLaptop(Laptop laptop) {
        try {
            equipoDAO.insertarEquipo(laptop);
            System.out.println("Laptop registrada exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al registrar Laptop: " + e.getMessage());
            // Aquí podrías manejar la excepción de manera más sofisticada, como mostrar un diálogo en la vista
        }
    }

    // Método para listar todos los equipos
    public List<Equipo> listarEquipos() throws SQLException {
        return equipoDAO.listarEquipos();
    }

    // Otros métodos como actualizar, eliminar y buscar equipos pueden agregarse aquí
}
