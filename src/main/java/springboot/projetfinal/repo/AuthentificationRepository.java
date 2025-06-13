package springboot.projetfinal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.projetfinal.model.Authentification;

public interface AuthentificationRepository extends JpaRepository<Authentification, Integer> {
}
