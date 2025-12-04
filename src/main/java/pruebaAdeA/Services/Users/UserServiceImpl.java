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
        // validate email
        if (userRepository.existsByLogin(request.getLogin())){
            throw new RuntimeException("Este login ya existe");
        }

        if (userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("El correo ya existe");
        }

        // Set data
        UserModel user = new UserModel();

        user.setLogin(request.getLogin());
        user.setPassword(PasswordUtil.encrypt(request.getPassword()));
        user.setNombre(request.getNombre());
        user.setApellidoPaterno(request.getApellidoPaterno());
        user.setApellidoMaterno(request.getApellidoMaterno());
        user.setEmail(request.getEmail());
        user.setStatus(StatusUserModel.A);
        user.setFechaAlta(LocalDate.now());

        UserModel saved = userRepository.save(user);

        return mapToDTO(saved);
    }

    @Override
    public UserResponseDto createFullUser(UserRequestDto request) {
        // validate email
        if (userRepository.existsByLogin(request.getLogin())){
            throw new RuntimeException("Este login ya existe");
        }

        if (userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("El correo ya existe");
        }
        // Validate area
        AreaModel area = areaRepository.findById(request.getAreaId())
                .orElseThrow(() -> new RuntimeException("Área no encontrada"));
        // Set data
        UserModel user = new UserModel();

        user.setLogin(request.getLogin());
        user.setPassword(PasswordUtil.encrypt(request.getPassword()));
        user.setNombre(request.getNombre());
        user.setApellidoPaterno(request.getApellidoPaterno());
        user.setApellidoMaterno(request.getApellidoMaterno());
        user.setEmail(request.getEmail());
        user.setStatus(StatusUserModel.A);
        user.setFechaAlta(LocalDate.now());
        user.setFechaVigencia(request.getFechaVigencia());
        user.setArea(area);

        UserModel saved = userRepository.save(user);

        return mapToDTO(saved);
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

        user.setArea(area);
        if (request.getFechaVigencia() != null) {
            user.setFechaVigencia(user.getFechaVigencia());
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

    @Override
    public List<UserResponseDto> listAllUser() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // validate email
    @Override
    public boolean validateEmail(String email, Integer id) {
        return !userRepository.existsByEmail(email);
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
        if (user.getClientesRegistrados() != null && !user.getClientesRegistrados().isEmpty()) {
            List<String> nombres = user.getClientesRegistrados()
                    .stream()
                    .map(ClientModel::getNombreCliente)
                    .collect(Collectors.toList());
            dto.setClienteNombre(nombres);
        }


        return  dto;
    }
}
