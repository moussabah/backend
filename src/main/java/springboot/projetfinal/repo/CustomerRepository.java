package springboot.projetfinal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.projetfinal.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
