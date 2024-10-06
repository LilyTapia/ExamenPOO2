/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patrones;

/**
 *
 * @author lilianatapia
 */

public class DescuentoPorcentaje implements Descuento {

    private Descuento descuento;
    private double porcentaje;

    public DescuentoPorcentaje(Descuento descuento, double porcentaje) {
        this.descuento = descuento;
        this.porcentaje = porcentaje;
    }

    @Override
    public double aplicarDescuento(double precio) {
        double precioConDescuento = precio - (precio * (porcentaje / 100));
        return descuento.aplicarDescuento(precioConDescuento);
    }
}
