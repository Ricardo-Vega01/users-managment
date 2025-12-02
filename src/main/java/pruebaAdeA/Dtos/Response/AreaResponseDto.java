package pruebaAdeA.Dtos.Response;

public class AreaResponseDto {
    private Integer id;
    private String nombreArea;

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
}
