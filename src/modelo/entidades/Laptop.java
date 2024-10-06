/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.entidades;

/**
 *
 * @author lilianatapia
 */
public class Laptop extends Equipo {

    private double tamanoPantalla;
    private boolean touch;
    private int cantidadPuertosUSB;

    public Laptop(int id, String descripcionModelo, String cpu, int discoDuroMB, int ramGB, double precio,
            double tamanoPantalla, boolean touch, int cantidadPuertosUSB) {
        super(id, descripcionModelo, cpu, discoDuroMB, ramGB, precio);
        this.tamanoPantalla = tamanoPantalla;
        this.touch = touch;
        this.cantidadPuertosUSB = cantidadPuertosUSB;
    }

    // Getters y Setters
    public double getTamanoPantalla() {
        return tamanoPantalla;
    }

    public void setTamanoPantalla(double tamanoPantalla) {
        this.tamanoPantalla = tamanoPantalla;
    }

    public boolean isTouch() {
        return touch;
    }

    public void setTouch(boolean touch) {
        this.touch = touch;
    }

    public int getCantidadPuertosUSB() {
        return cantidadPuertosUSB;
    }

    public void setCantidadPuertosUSB(int cantidadPuertosUSB) {
        this.cantidadPuertosUSB = cantidadPuertosUSB;
    }

    @Override
    public String getTipoEquipo() {
        return "Laptop";
    }
}
