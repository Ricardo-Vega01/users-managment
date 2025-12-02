package pruebaAdeA.Services.Clients;

import pruebaAdeA.Dtos.Request.ClientRequestDto;
import pruebaAdeA.Dtos.Response.ClientResponseDto;

import java.util.List;

public interface ClientService {
    //create client
    ClientResponseDto createClient(ClientRequestDto request);

    //update client
    ClientResponseDto updateClient(Integer id, ClientRequestDto request);

    //get client by Id
    ClientResponseDto getClientById(Integer id);

    //delete client
    void deleteClient(Integer id);

    // list clients
    List<ClientResponseDto> getAllClients();

    // list client by created user
    List<ClientResponseDto> getClientsByUser(Integer userId);
}
