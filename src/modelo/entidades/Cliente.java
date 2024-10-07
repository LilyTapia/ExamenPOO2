package modelo.entidades;

public class Cliente {

    private String rut;
    private String nombreCompleto;
    private String direccion;
    private String comuna;
    private String email;
    private String telefono;

    // Constructor
    public Cliente(String rut, String nombreCompleto, String direccion, String comuna, String email, String telefono) {
        this.rut = rut;
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.comuna = comuna;
        setEmail(email);
        setTelefono(telefono);
    }

    // Getters y Setters
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    public String getTelefono() {  // Changed to String
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono != null && telefono.matches("\\+?\\d+")) {
            this.telefono = telefono;
        } else {
            throw new IllegalArgumentException("Invalid phone number format");
        }
    }
}
