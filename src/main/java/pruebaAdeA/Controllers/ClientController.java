package pruebaAdeA.Controllers;

import org.springframework.web.bind.annotation.*;
import pruebaAdeA.Dtos.Request.ClientRequestDto;
import pruebaAdeA.Dtos.Response.ClientResponseDto;
import pruebaAdeA.Services.Clients.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping
    public ClientResponseDto createClient(@RequestBody ClientRequestDto request) {
        return clientService.createClient(request);
    }

    @PutMapping("/{id}")
    public ClientResponseDto updateClient(@PathVariable Integer id, @RequestBody ClientRequestDto request) {
        return clientService.updateClient(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Integer id) {
        clientService.deleteClient(id);
    }

    @GetMapping("/{id}")
    public ClientResponseDto getClientById(@PathVariable Integer id) {
        return clientService.getClientById(id);
    }

    @GetMapping
    public List<ClientResponseDto> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/user/{userId}")
    public List<ClientResponseDto> getClientByUser(@PathVariable Integer userId) {
        return clientService.getClientsByUser(userId);
    }
}
