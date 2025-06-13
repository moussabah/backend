package springboot.projetfinal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.projetfinal.model.OrderLine;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {

}
