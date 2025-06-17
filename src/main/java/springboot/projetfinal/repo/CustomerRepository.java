package springboot.projetfinal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.projetfinal.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByLoginAndPassword(String login, String password);
    Optional<Customer> findByLogin(String login);
}
