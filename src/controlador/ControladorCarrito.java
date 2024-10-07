package controlador;

import modelo.entidades.Equipo;
import java.util.ArrayList;
import java.util.List;

public class ControladorCarrito {
    private List<Equipo> carrito;

    public ControladorCarrito() {
        this.carrito = new ArrayList<>();
    }

    // Método para agregar un producto al carrito
    public void agregarProducto(Equipo equipo) {
        if (equipo != null) {
            carrito.add(equipo);
            System.out.println("Producto agregado al carrito: " + equipo.getDescripcionModelo());
        } else {
            System.err.println("No se puede agregar un producto nulo al carrito.");
        }
    }

    // Método para eliminar un producto del carrito
    public void eliminarProducto(Equipo equipo) {
        if (equipo != null && carrito.contains(equipo)) {
            carrito.remove(equipo);
            System.out.println("Producto eliminado del carrito: " + equipo.getDescripcionModelo());
        } else {
            System.err.println("El producto no está en el carrito o es nulo.");
        }
    }

    // Método para obtener todos los productos en el carrito
    public List<Equipo> getCarrito() {
        return new ArrayList<>(carrito);  // Return a copy to prevent direct modification
    }

    // Método para obtener el total del carrito
    public double obtenerTotal() {
        return carrito.stream()
                .mapToDouble(Equipo::getPrecio)
                .sum();
    }

    // Método para vaciar el carrito
    public void vaciarCarrito() {
        carrito.clear();
        System.out.println("El carrito ha sido vaciado.");
    }

    // Método para mostrar todos los productos del carrito
    public void mostrarCarrito() {
        if (carrito.isEmpty()) {
            System.out.println("El carrito está vacío.");
        } else {
            System.out.println("Productos en el carrito:");
            for (Equipo equipo : carrito) {
                System.out.println("- " + equipo.getDescripcionModelo() + " | Precio: $" + equipo.getPrecio());
            }
        }
    }

    // Otros métodos relacionados con el carrito pueden agregarse aquí
}
