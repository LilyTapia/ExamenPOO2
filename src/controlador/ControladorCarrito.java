/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.entidades.Equipo;
import java.util.ArrayList;
import java.util.List;

public class ControladorCarrito {
    private List<Equipo> carrito;

    public ControladorCarrito() {
        this.carrito = new ArrayList<>();
    }

    public void agregarProducto(Equipo equipo) {
        carrito.add(equipo);
        System.out.println("Producto agregado al carrito: " + equipo.getDescripcionModelo());
    }

    public void eliminarProducto(Equipo equipo) {
        carrito.remove(equipo);
        System.out.println("Producto eliminado del carrito: " + equipo.getDescripcionModelo());
    }

    public List<Equipo> getCarrito() {
        return carrito;
    }

    // Otros métodos relacionados con el carrito pueden agregarse aquí
}
