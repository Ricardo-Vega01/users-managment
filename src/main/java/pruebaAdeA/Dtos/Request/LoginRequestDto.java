package pruebaAdeA.Dtos.Request;

public class LoginRequestDto {
    private String login;
    private String password;
    private String email;
    private String userAgent;

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

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
