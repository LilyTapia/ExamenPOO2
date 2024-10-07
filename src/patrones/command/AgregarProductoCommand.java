package patrones.command;

import controlador.ControladorCarrito;
import modelo.entidades.Equipo;

public class AgregarProductoCommand implements CarritoCommand {
    private ControladorCarrito carrito;
    private Equipo equipo;

    public AgregarProductoCommand(ControladorCarrito carrito, Equipo equipo) {
        this.carrito = carrito;
        this.equipo = equipo;
    }

    @Override
    public void execute() {
        carrito.agregarProducto(equipo);
    }

    @Override
    public void undo() {
        carrito.eliminarProducto(equipo);
    }
}
