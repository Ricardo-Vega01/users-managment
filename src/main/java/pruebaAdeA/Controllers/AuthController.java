package pruebaAdeA.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import pruebaAdeA.Dtos.Request.LoginRequestDto;
import pruebaAdeA.Dtos.Response.LoginResponseDto;
import pruebaAdeA.Services.Login.LoginService;

import java.util.List;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final LoginService loginService;

    // Empty constructor
    public AuthController(LoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto request, HttpServletRequest httpRequest) {
        LoginResponseDto response = new LoginResponseDto();

        try {
            response = loginService.login(request, httpRequest);
        } catch (RuntimeException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @GetMapping("/history/{userId}")
    public List<LoginResponseDto> getLoginHistory(@PathVariable Integer userId) {
        return loginService.getLoginHistoryByUser(userId);
    }
}
