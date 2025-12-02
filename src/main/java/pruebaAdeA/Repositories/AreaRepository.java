package pruebaAdeA.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pruebaAdeA.Models.AreaModel;

import java.util.Optional;

public interface AreaRepository extends JpaRepository<AreaModel, Integer> {
    Optional<AreaModel> findByNombreArea(String nombreArea);
}
