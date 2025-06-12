package springboot.projetfinal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.projetfinal.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
}
