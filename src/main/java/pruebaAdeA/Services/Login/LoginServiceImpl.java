package pruebaAdeA.Services.Login;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import pruebaAdeA.Dtos.Request.LoginRequestDto;
import pruebaAdeA.Dtos.Response.LoginResponseDto;
import pruebaAdeA.Dtos.Response.UserResponseDto;
import pruebaAdeA.Models.LoginModel;
import pruebaAdeA.Models.StatusUserModel;
import pruebaAdeA.Models.UserModel;
import pruebaAdeA.Repositories.LoginRepository;
import pruebaAdeA.Repositories.UserRepository;
import pruebaAdeA.Utils.PasswordUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService{

    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    public LoginServiceImpl(UserRepository userRepository, LoginRepository loginRepository){
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto request, HttpServletRequest httpRequest) {
        UserModel user = userRepository.findByLogin(request.getLogin())
                .orElseGet(() -> userRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

        String passEncrypt = PasswordUtil.encrypt(request.getPassword());
        boolean exitoso = user.getPassword().equals(passEncrypt);

        LoginModel login = new LoginModel();
        login.setUser(user);
        login.setFechaLogin(LocalDateTime.now());
        login.setIpAddres(httpRequest.getRemoteAddr());
        login.setUserAgent(httpRequest.getHeader("User-Agent"));
        login.setAccess(exitoso);

        if (!exitoso) {
            int intentosFallidos = loginRepository.findByUserId(user.getId())
                    .stream()
                    .mapToInt(LoginModel::getIntentosFallidos)
                    .max()
                    .orElse(0);
            login.setIntentosFallidos(intentosFallidos + 1);
            login.setNumeroSession(0);
        } else {
            int numeroSesion = loginRepository.findByUserId(user.getId())
                    .stream()
                    .mapToInt(LoginModel::getNumeroSession)
                    .max()
                    .orElse(0);
            login.setNumeroSession(numeroSesion + 1);
            login.setIntentosFallidos(0);
        }

        loginRepository.save(login);


        if (!exitoso) {
            return mapToDTO(login);
        }

        if (user.getStatus() != StatusUserModel.A) {
            throw new RuntimeException("Usuario no activo");
        }
        if (user.getFechaVigencia() != null && user.getFechaVigencia().isBefore(LocalDate.now())) {
            user.setStatus(StatusUserModel.R);
            user.setFechaRevocado(LocalDate.now());
            userRepository.save(user);
            throw new RuntimeException("Usuario vencido / revocado");
        }

        return mapToDTO(login);
    }

    @Override
    public List<LoginResponseDto> getLoginHistoryByUser(Integer userId) {
        return loginRepository.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private LoginResponseDto mapToDTO(LoginModel login){
        LoginResponseDto dto = new LoginResponseDto();

        dto.setSuccess(login.isAccess());
        dto.setMessage(login.isAccess() ? "Login exitoso" : "Contraseña incorrecta");
        dto.setFechaLogin(login.getFechaLogin());
        dto.setExitoso(login.isAccess());
        dto.setNumeroSesion(login.getNumeroSession());
        dto.setIntentosFallidos(login.getIntentosFallidos());

        dto.setIpAddress(login.getIpAddres());
        dto.setUserAgent(login.getUserAgent());

        dto.setUsuario(mapUserToDTO(login.getUser()));

        return dto;
    }

    private UserResponseDto mapUserToDTO(UserModel user) {
        UserResponseDto userDto = new UserResponseDto();

        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setEmail(user.getEmail());
        userDto.setNombreCompleto(user.getNombre() + " " + user.getApellidoPaterno());

        return userDto;
    }

}
