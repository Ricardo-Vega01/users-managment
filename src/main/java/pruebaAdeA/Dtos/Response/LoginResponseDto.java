package pruebaAdeA.Dtos.Response;

public class LoginResponseDto {
    private boolean success;
    private String message;

    // data user
    private UserResponseDto usuario;

    // Emty constructor
    public LoginResponseDto(){}

    // Getters y Setters
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
}
