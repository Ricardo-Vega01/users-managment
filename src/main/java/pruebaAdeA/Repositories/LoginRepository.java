package pruebaAdeA.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pruebaAdeA.Models.LoginModel;

import java.util.List;
import java.util.Optional;

public interface LoginRepository extends JpaRepository<LoginModel, Integer> {
    List<LoginModel> findByUserId(Integer userId);
}
