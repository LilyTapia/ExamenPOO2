package patrones.decorator;

import modelo.entidades.Equipo;
import java.util.List;

public class DescuentoFijo extends DescuentoDecorator {
    private double descuentoFijo;

    public DescuentoFijo(List<Equipo> carrito, double descuentoFijo) {
        super(carrito);
        this.descuentoFijo = descuentoFijo;
    }

    @Override
    public double aplicarDescuento() {
        double total = obtenerTotal();
        return Math.max(total - descuentoFijo, 0); // Ensure total doesn't go below 0
    }
}
