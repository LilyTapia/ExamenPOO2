/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patrones;

import controlador.ControladorCarrito;
import modelo.entidades.Equipo;

public class AgregarProductoCommand implements Command {

    private ControladorCarrito controladorCarrito;
    private Equipo equipo;

    public AgregarProductoCommand(ControladorCarrito controladorCarrito, Equipo equipo) {
        this.controladorCarrito = controladorCarrito;
        this.equipo = equipo;
    }

    @Override
    public void execute() {
        controladorCarrito.agregarProducto(equipo);
    }
}
