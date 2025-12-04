package pruebaAdeA.Dtos.Request;

public class AreaRequestDto {
    private String nombre;      // coincide con el front
    private String descripcion; // coincide con el front

    //Empty constructor
    AreaRequestDto(){}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

