package pruebaAdeA.Dtos.Request;

public class UserRequestDto {

    // user data
    private String login;
    private String password;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
    private String fechaVigencia;

    // fk - area
    private Integer areaId;

    // fk - client
    private Integer clientId;

    // Empty constructor
    public UserRequestDto(){}

    // Getters y Setters
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String apellidoPaterno) { this.apellidoPaterno = apellidoPaterno; }

    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String apellidoMaterno) { this.apellidoMaterno = apellidoMaterno; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getAreaId() { return areaId; }
    public void setAreaId(Integer areaId) { this.areaId = areaId; }

    public Integer getClienteId() { return clientId; }
    public void setClienteId(Integer clienteId) { this.clientId = clienteId; }

    public String getFechaVigencia() { return fechaVigencia; }
    public void setFechaVigencia(String fechaVigencia) { this.fechaVigencia = fechaVigencia; }

}
