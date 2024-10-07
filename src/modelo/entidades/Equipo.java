package modelo.entidades;

public abstract class Equipo {
    private int id;
    private String descripcionModelo;
    private String cpu;
    private int discoDuroMB;
    private int ramGB;
    private double precio;

    // Constructor
    public Equipo(int id, String descripcionModelo, String cpu, int discoDuroMB, int ramGB, double precio) {
        this.id = id;
        this.descripcionModelo = descripcionModelo;
        this.cpu = cpu;
        this.discoDuroMB = discoDuroMB;
        this.ramGB = ramGB;
        this.precio = precio;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcionModelo() {
        return descripcionModelo;
    }

    public void setDescripcionModelo(String descripcionModelo) {
        this.descripcionModelo = descripcionModelo;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public int getDiscoDuroMB() {
        return discoDuroMB;
    }

    public void setDiscoDuroMB(int discoDuroMB) {
        this.discoDuroMB = discoDuroMB;
    }

    public int getRamGB() {
        return ramGB;
    }

    public void setRamGB(int ramGB) {
        this.ramGB = ramGB;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Abstract method to get the type of equipment
    public abstract String getTipoEquipo();
}
