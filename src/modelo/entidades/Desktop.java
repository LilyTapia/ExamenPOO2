/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.entidades;

/**
 *
 * @author lilianatapia
 */
public class Desktop extends Equipo {

    private String potenciaFuente;
    private String factorForma;

    public Desktop(int id, String descripcionModelo, String cpu, int discoDuroMB, int ramGB, double precio,
            String potenciaFuente, String factorForma) {
        super(id, descripcionModelo, cpu, discoDuroMB, ramGB, precio);
        this.potenciaFuente = potenciaFuente;
        this.factorForma = factorForma;
    }

    // Getters y Setters
    public String getPotenciaFuente() {
        return potenciaFuente;
    }

    public void setPotenciaFuente(String potenciaFuente) {
        this.potenciaFuente = potenciaFuente;
    }

    public String getFactorForma() {
        return factorForma;
    }

    public void setFactorForma(String factorForma) {
        this.factorForma = factorForma;
    }

    @Override
    public String getTipoEquipo() {
        return "Desktop";
    }
}
