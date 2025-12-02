package pruebaAdeA.Models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "area")
public class AreaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombreArea;

    @Column(nullable = false, unique = true)
    private String codigoInterno;

    @Column(nullable = false)
    private LocalDate fechaAlta;

    //Empty constructor
    public AreaModel(){}

    // Setters y Getters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreArea() {
        return nombreArea;
    }
    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }
    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }
    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
}
