package pruebaAdeA.Dtos.Response;

import pruebaAdeA.Models.ClientModel;

import java.util.List;

public class UserResponseDto {

    private Integer id;
    private String login;
    private String nombreCompleto;
    private String email;
    private String status;
    private String fechaAlta;
    private String fechaVigencia;

    // match fk
    private String areaNombre;
    private List<String> clienteNombre;

    // Empty constructor
    public UserResponseDto(){}

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(String fechaAlta) { this.fechaAlta = fechaAlta; }

    public String getFechaVigencia() { return fechaVigencia; }
    public void setFechaVigencia(String fechaVigencia) { this.fechaVigencia = fechaVigencia; }

    public String getAreaNombre() { return areaNombre; }
    public void setAreaNombre(String areaNombre) { this.areaNombre = areaNombre; }

    public List<String> getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(List<String> clienteNombre) {
        this.clienteNombre = clienteNombre;
    }
}
