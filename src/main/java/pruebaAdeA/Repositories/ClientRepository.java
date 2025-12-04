package pruebaAdeA.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pruebaAdeA.Models.ClientModel;
import pruebaAdeA.Models.UserModel;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientModel, Integer> {
    // Find client created by user
    List<ClientModel> findByCreadoPorId(Integer userId);
}
