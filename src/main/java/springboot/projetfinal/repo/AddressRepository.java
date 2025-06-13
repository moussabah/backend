package springboot.projetfinal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.projetfinal.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
