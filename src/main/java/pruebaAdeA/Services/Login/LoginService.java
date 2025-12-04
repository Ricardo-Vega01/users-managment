package pruebaAdeA.Services.Login;

import jakarta.servlet.http.HttpServletRequest;
import pruebaAdeA.Dtos.Request.LoginRequestDto;
import pruebaAdeA.Dtos.Response.LoginResponseDto;

import java.util.List;


public interface LoginService {

    LoginResponseDto login(LoginRequestDto request, HttpServletRequest httpRequest);
    List<LoginResponseDto> getLoginHistoryByUser(Integer userId);
}
