package modelo.entidades;

import java.util.Date;

public class Venta {

    private int id;
    private Cliente cliente;
    private Equipo equipo;
    private Date fechaHora;
    private double total;

    // Constructor
    public Venta(int id, Cliente cliente, Equipo equipo, Date fechaHora) {
        this.id = id;
        this.cliente = cliente;
        this.equipo = equipo;
        this.fechaHora = fechaHora;
        this.total = calcularTotal();  // Calculate total upon initialization
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) { 
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
        this.total = calcularTotal();  // Recalculate total when the equipo changes
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public double getTotal() {
        return total;
    }

    // Método to calculate the total price based on the equipment price
    public double calcularTotal() {
        return equipo != null ? equipo.getPrecio() : 0;
    }

    public void setTotal(double total) {
        this.total = total;  // Optionally set total if needed in a specific context
    }
}
