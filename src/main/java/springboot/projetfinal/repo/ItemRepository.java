package springboot.projetfinal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.projetfinal.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
