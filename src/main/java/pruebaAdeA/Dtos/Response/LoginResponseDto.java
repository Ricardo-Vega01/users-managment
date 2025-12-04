package pruebaAdeA.Dtos.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LoginResponseDto {
    private boolean success;
    private String message;
    private UserResponseDto usuario;

    // Datos del historial
    private LocalDateTime fechaLogin;
    private boolean exitoso;
    private int numeroSesion;
    private int intentosFallidos;
    private String ipAddress;
    private String userAgent;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserResponseDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UserResponseDto usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaLogin() {
        return fechaLogin;
    }

    public void setFechaLogin(LocalDateTime fechaLogin) {
        this.fechaLogin = fechaLogin;
    }

    public boolean isExitoso() {
        return exitoso;
    }

    public void setExitoso(boolean exitoso) {
        this.exitoso = exitoso;
    }

    public int getNumeroSesion() {
        return numeroSesion;
    }

    public void setNumeroSesion(int numeroSesion) {
        this.numeroSesion = numeroSesion;
    }

    public int getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(int intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }

    public String getIpAddress(){
        return  ipAddress;
    }

    public void setIpAddress(String ipAddress){
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
