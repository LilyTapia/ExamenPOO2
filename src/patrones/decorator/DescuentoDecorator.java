package patrones.decorator;

import modelo.entidades.Equipo;
import java.util.List;

public abstract class DescuentoDecorator {
    protected List<Equipo> carrito;

    public DescuentoDecorator(List<Equipo> carrito) {
        this.carrito = carrito;
    }

    // Método abstracto para calcular el descuento
    public abstract double aplicarDescuento();

    // Obtener el total antes del descuento
    public double obtenerTotal() {
        return carrito.stream().mapToDouble(Equipo::getPrecio).sum();
    }
}
