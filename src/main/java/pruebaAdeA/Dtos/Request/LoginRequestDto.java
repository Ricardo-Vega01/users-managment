package pruebaAdeA.Dtos.Request;

public class LoginRequestDto {
    private String login;
    private String password;

    // empty constructor
    public LoginRequestDto(){}

    // Setters y Getters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
