package pruebaAdeA.Services.Users;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pruebaAdeA.Dtos.Request.UserRequestDto;
import pruebaAdeA.Dtos.Response.UserResponseDto;
import pruebaAdeA.Models.AreaModel;
import pruebaAdeA.Models.ClientModel;
import pruebaAdeA.Models.StatusUserModel;
import pruebaAdeA.Models.UserModel;
import pruebaAdeA.Repositories.AreaRepository;
import pruebaAdeA.Repositories.ClientRepository;
import pruebaAdeA.Repositories.UserRepository;
import pruebaAdeA.Utils.PasswordUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    // Access to other params from repos
    private final UserRepository userRepository;
    private final AreaRepository areaRepository;
    private final ClientRepository clientRepository;

    // Constructor
    public UserServiceImpl(UserRepository userRepository, AreaRepository areaRepository, ClientRepository clientRepository){
        this.userRepository = userRepository;
        this.areaRepository = areaRepository;
        this.clientRepository = clientRepository;
    }

    // Create User
    @Override
    public UserResponseDto createUser(UserRequestDto request) {
        // validate dtos
        if (userRepository.existsByLogin(request.getLogin())){
            throw new RuntimeException("Este login ya existe");
        }

        if (userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("El correo ya existe");
        }

        // Get area & clients
        AreaModel area = areaRepository.findById(request.getAreaId())
                .orElseThrow(() -> new RuntimeException("Área no encontrada"));

        ClientModel client = clientRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // SET DATA
        UserModel user = new UserModel();

        user.setLogin(request.getLogin());
        user.setPassword(PasswordUtil.encrypt(request.getPassword()));
        user.setNombre(request.getNombre());
        user.setApellidoPaterno(request.getApellidoPaterno());
        user.setApellidoMaterno(request.getApellidoMaterno());
        user.setEmail(request.getEmail());
        user.setArea(area);
        user.setFechaAlta(LocalDate.now());
        user.setStatus(StatusUserModel.A);
        user.setCliente(client);

        // validate end dates
        if (request.getFechaVigencia() != null && request.getFechaVigencia().isEmpty()){
            user.setFechaVigencia(LocalDate.parse(request.getFechaVigencia()));
        }

        UserModel save = userRepository.save(user);

        return mapToDTO(save);
    }

    @Override
    public UserResponseDto updateUser(Integer id, UserRequestDto request) {

        UserModel user = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("El usuario no existe"));

        // validate email & login
        if (!user.getLogin().equals(request.getLogin()) && userRepository.existsByLogin(request.getLogin())) {
            throw new RuntimeException("Login ya existe");
        }
        if (!user.getEmail().equals(request.getEmail()) && userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email ya existe");
        }

        // update data
        user.setLogin(request.getLogin());

        // validate password
        if (request.getPassword()!=null&&request.getPassword().isEmpty()){
            user.setPassword(PasswordUtil.encrypt(request.getPassword()));
        }

        user.setNombre(request.getNombre());
        user.setApellidoPaterno(request.getApellidoPaterno());
        user.setApellidoMaterno(request.getApellidoMaterno());
        user.setEmail(request.getEmail());
        user.setFechaModificacion(LocalDate.now());

        // Update area & cliens
        AreaModel area = areaRepository.findById(request.getAreaId())
                .orElseThrow(() -> new RuntimeException("Area no encontrada"));

        ClientModel client = clientRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        user.setArea(area);
        user.setCliente(client);

        if (request.getFechaVigencia() != null && !request.getFechaVigencia().isEmpty()) {
            user.setFechaVigencia(LocalDate.parse(request.getFechaVigencia()));
        }

        UserModel update = userRepository.save(user);
        return mapToDTO(update);
    }

    /*
    * In this method not delete the user, just change he's state
    * */
    @Override
    public void DeleteUser(Integer id) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El usuario no existe"));

        user.setStatus(StatusUserModel.B);
        user.setFechaVigencia(LocalDate.now());

        userRepository.save(user);
    }

    // Get for id
    @Override
    public UserResponseDto getUserById(Integer id) {
        UserModel usser = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("El usuario no se encontro"));

        return mapToDTO(usser);
    }

    // Get users list for status
    @Override
    public List<UserResponseDto> listUserByStatus(StatusUserModel status) {
        List<UserModel> users = userRepository.findByStatus(status);
        return users.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    // calidate login
    @Override
    public boolean validateLogin(String login, Integer id) {
        return !userRepository.existsByLogin(login);
    }

    // validate email
    @Override
    public boolean validateEmail(String email, Integer id) {
        return !userRepository.existsByEmail(email);
    }

    // Login
    @Override
    public UserResponseDto login(String loginEmail, String password) {
        UserModel user = userRepository.findByLogin(loginEmail)
                .orElseGet(() -> userRepository.findByEmail(loginEmail)
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

        // validate password
        String passEncrypt = PasswordUtil.encrypt(password);
        if (!user.getPassword().equals(passEncrypt)) {
            user.setIntentos(user.getIntentos() + 1);
            userRepository.save(user);
            throw new RuntimeException("Contraseña incorrecta");
        }

        // validate status
        if (user.getStatus() != StatusUserModel.A) {
            throw new RuntimeException("Usuario no activo");
        }

        // validate date
        if (user.getFechaVigencia() != null && user.getFechaVigencia().isBefore(LocalDate.now())) {
            user.setStatus(StatusUserModel.R);
            user.setFechaRevocado(LocalDate.now());
            userRepository.save(user);
            throw new RuntimeException("Usuario vencido / revocado");
        }

        // success login
        user.setIntentos(0);
        user.setNoAccesos(user.getNoAccesos() + 1);
        userRepository.save(user);

        return mapToDTO(user);
    }

    // Relation Entity - DTO
    private UserResponseDto mapToDTO(UserModel user){
        UserResponseDto dto = new UserResponseDto();

        dto.setId(user.getId());
        dto.setLogin(user.getLogin());
        dto.setNombreCompleto(user.getNombre() + " " + user.getApellidoPaterno() + " " + user.getApellidoMaterno());
        dto.setEmail(user.getEmail());
        dto.setStatus(user.getStatus().name());
        dto.setFechaAlta(user.getFechaAlta() != null ? user.getFechaAlta().toString() : null);
        dto.setFechaVigencia(user.getFechaVigencia() != null ? user.getFechaVigencia().toString() : null);

        if (user.getArea() != null) dto.setAreaNombre(user.getArea().getNombreArea());
        if (user.getCliente() != null) dto.setClienteNombre(user.getCliente().getNombreCliente());

        return  dto;
    }
}
