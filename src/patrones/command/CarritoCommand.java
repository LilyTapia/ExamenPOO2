package patrones.command;

import controlador.ControladorCarrito;

public interface CarritoCommand {
    void execute();
    void undo();
}
