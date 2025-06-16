package springboot.projetfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.projetfinal.model.Customer;
import springboot.projetfinal.model.Ingredient;
import springboot.projetfinal.model.Item;
import springboot.projetfinal.repo.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public List<Item> findAll() {
        return repository.findAll();
    }

    public Optional<Item> findById(int id) {
        return repository.findById(id);
    }

    public Item save(Item item) {
        return repository.save(item);
    }

    public void deleteById(int id) {
        Item item = repository.findById(id).orElseThrow();
        for (Customer customer : item.getCustomers() ){
            customer.getItems().remove(item);
        }
        for (Ingredient ingredient : item.getIngredients() ){
            ingredient.getItems().remove(item);
        }

        repository.deleteById(id);
    }

    public Item update(Item updatedItem) {
        Optional<Item> optionalItem = repository.findById(updatedItem.getRef());
        if (optionalItem.isEmpty()) {
            throw new RuntimeException("Item non trouvé avec ref: " + updatedItem.getRef());
        }

        Item existingItem = optionalItem.get();
        // 🔁 Met à jour uniquement le rating
        updateRating(existingItem, updatedItem.getRate());
        return repository.save(existingItem);

    }

    // ✅ Méthode de mise à jour du rating
    private void updateRating(Item item, int newRating) {
        if (newRating > 0) {
            int totalRate = item.getRate() * item.getNbRate();
            int newNbRate = item.getNbRate() + 1;
            int newRate = Math.round((float)(totalRate + newRating) / newNbRate);
            item.setNbRate(newNbRate);
            item.setRate(newRate);
        }
    }

}
