package patrones.decorator;

import modelo.entidades.Equipo;
import java.util.List;

public class DescuentoPorcentaje extends DescuentoDecorator {
    private double porcentajeDescuento;

    public DescuentoPorcentaje(List<Equipo> carrito, double porcentajeDescuento) {
        super(carrito);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public double aplicarDescuento() {
        double total = obtenerTotal();
        return total - (total * porcentajeDescuento / 100);
    }
}
