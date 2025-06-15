package springboot.projetfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.projetfinal.model.Customer;
import springboot.projetfinal.model.Ingredient;
import springboot.projetfinal.model.Item;
import springboot.projetfinal.repo.IngredientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository repository;

    public List<Ingredient> findAll() {
        return repository.findAll();
    }

    public Optional<Ingredient> findById(int id) {
        return repository.findById(id);
    }

    public Ingredient save(Ingredient ingredient) {
        return repository.save(ingredient);
    }

    public void deleteById(int id) {

        Ingredient ingredient = repository.findById(id).orElseThrow();

        for (Item item : ingredient.getItems() ){
            item.getIngredients().remove(ingredient);
        }

        repository.deleteById(id);
    }
    public Ingredient update(Ingredient ingredient) {
        return repository.save(ingredient);
    }
}
