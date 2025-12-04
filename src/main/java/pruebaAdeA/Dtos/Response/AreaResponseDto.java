package pruebaAdeA.Dtos.Response;

import java.time.LocalDate;

public class AreaResponseDto {
    private Integer id;
    private String nombreArea;
    private String codigoInterno;
    private LocalDate fechaAlta;

    // Empty Constructor
    public AreaResponseDto(){}

    // Setters y Getters
    public Integer getId(){
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

    public String getCodigoInterno(){
        return codigoInterno;
    }

    public void setCodigoInterno(String codigoInterno){
        this.codigoInterno = codigoInterno;
    }

    public LocalDate getFechaAlta(){
        return  fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
}
