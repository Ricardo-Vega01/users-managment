package pruebaAdeA.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pruebaAdeA.Models.StatusUserModel;
import pruebaAdeA.Models.UserModel;

import java.util.List;
import java.util.Optional;

// Access to methods for users
@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    // Find user by login
    Optional<UserModel> findByLogin(String login);

    // Find user by email
    Optional<UserModel> findByEmail(String email);

    // Find user by status
    List<UserModel> findByStatus(StatusUserModel status);

    // Find user by name
    List<UserModel> findByNombre(String nombre);

    // Find user by name area
    List<UserModel> findByAreaNombreArea(String nombreArea);

    // validate email
    boolean existsByEmail(String email);

    // validate login
    boolean existsByLogin(String login);
}
