package springboot.projetfinal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.projetfinal.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
