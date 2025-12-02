package pruebaAdeA.Dtos.Request;

import java.time.LocalDate;

public class ClientRequestDto {
    private String nombreCliente;
    private Integer creadorId;
    private String descripcion;
    private LocalDate fechaAlta;

    // Empty Constructor
    public ClientRequestDto(){}

    // Setters y Getters
    public String getNombreCliente(){
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Integer getCreadorId() {
        return creadorId;
    }

    public void setCreadorId(Integer creadorId) {
        this.creadorId = creadorId;
    }

    public Integer getCreadorNombre() { return creadorId; }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
}
