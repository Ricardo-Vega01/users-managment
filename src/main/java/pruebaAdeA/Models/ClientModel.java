package pruebaAdeA.Models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "client")
public class ClientModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombreCliente;

    private String descripcion;

    @Column(nullable = false)
    private LocalDate fechaAlta;

    @ManyToOne
    @JoinColumn(name = "creado_por", nullable = false)
    private UserModel creadoPor;

    //Empty constructor
    public ClientModel(){}

    // Getters y Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

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

    public UserModel getCreadoPor() {
        return creadoPor;
    }
    public void setCreadoPor(UserModel creadoPor) {
        this.creadoPor = creadoPor;
    }
}
