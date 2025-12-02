package pruebaAdeA.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pruebaAdeA.Dtos.Request.LoginRequestDto;
import pruebaAdeA.Dtos.Response.LoginResponseDto;
import pruebaAdeA.Dtos.Response.UserResponseDto;
import pruebaAdeA.Services.Users.UserService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    // constructor
    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto request){
        LoginResponseDto response = new LoginResponseDto();

        try{
            UserResponseDto user = userService.login(request.getLogin(), request.getPassword());
            response.setSuccess(true);
            response.setUsuario(user);
        } catch (RuntimeException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
