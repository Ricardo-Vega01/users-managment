package pruebaAdeA.Services.Clients;

import org.springframework.stereotype.Service;
import pruebaAdeA.Dtos.Request.ClientRequestDto;
import pruebaAdeA.Dtos.Response.ClientResponseDto;
import pruebaAdeA.Models.ClientModel;
import pruebaAdeA.Models.UserModel;
import pruebaAdeA.Repositories.ClientRepository;
import pruebaAdeA.Repositories.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    // constrctor
    public ClientServiceImpl(ClientRepository clientRepository, UserRepository userRepository){
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ClientResponseDto createClient(ClientRequestDto request) {
        UserModel created = userRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Usuario creador por defecto no existe"));

        ClientModel client = new ClientModel();
        client.setNombreCliente(request.getNombreCliente());
        client.setDescripcion(request.getDescripcion());
        client.setCreadoPor(created);
        client.setFechaAlta(LocalDate.now());

        ClientModel saved = clientRepository.save(client);
        return mapToDTO(saved);
    }

    @Override
    public ClientResponseDto updateClient(Integer id, ClientRequestDto request) {
        ClientModel client = clientRepository.findById(id)
                .orElseThrow(()->new RuntimeException("El cliente no existe"));

        client.setNombreCliente(request.getNombreCliente());
        client.setDescripcion(client.getDescripcion());

        if (request.getCreadorNombre() != null){
            UserModel created = userRepository.findById(request.getCreadorId())
                    .orElseThrow(()->new RuntimeException("El usuario no existe"));
        }

        ClientModel update = clientRepository.save(client);

        return mapToDTO(update);
    }

    @Override
    public ClientResponseDto getClientById(Integer id) {
        ClientModel client = clientRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No se encontro informaciṕn"));

        return mapToDTO(client);
    }

    @Override
    public void deleteClient(Integer id) {
         clientRepository.deleteById(id);
    }

    @Override
    public List<ClientResponseDto> getAllClients() {
        return clientRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ClientResponseDto> getClientsByUser(Integer userId) {
        return clientRepository.findByCreadoPorId(userId).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private ClientResponseDto mapToDTO(ClientModel client) {
        ClientResponseDto dto = new ClientResponseDto();

        dto.setId(client.getId());
        dto.setNombreCliente(client.getNombreCliente());
        dto.setDescripcion(client.getDescripcion());
        dto.setFechaAlta(client.getFechaAlta());

        if (client.getCreadoPor() != null) {
            dto.setCreadorNombre(
                    client.getCreadoPor().getNombre() + " " + client.getCreadoPor().getApellidoMaterno()
            );
        }

        return dto;
    }

}
