package patrones.command;

import controlador.ControladorCarrito;
import modelo.entidades.Equipo;

public class EliminarProductoCommand implements CarritoCommand {
    private ControladorCarrito carrito;
    private Equipo equipo;

    public EliminarProductoCommand(ControladorCarrito carrito, Equipo equipo) {
        this.carrito = carrito;
        this.equipo = equipo;
    }

    @Override
    public void execute() {
        carrito.eliminarProducto(equipo);
    }

    @Override
    public void undo() {
        carrito.agregarProducto(equipo);
    }
}
