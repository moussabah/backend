package springboot.projetfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.projetfinal.model.*;
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
        if (item.getOrder_Lines() != null) {
            for (OrderLine orderLine : item.getOrder_Lines()) {
                orderLine.setItem(item); // 🟢 Réassocier le lien
            }
        }
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
    public Item update(Item item) {
        if (item.getOrder_Lines() != null) {
            for (OrderLine orderLine : item.getOrder_Lines()) {
                orderLine.setItem(item); // 🟢 Réassocier le lien
            }
        }
        return repository.save(item);
    }
}
