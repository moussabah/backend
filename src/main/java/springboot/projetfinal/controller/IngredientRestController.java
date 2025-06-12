package springboot.projetfinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.projetfinal.model.Ingredient;
import springboot.projetfinal.service.IngredientService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ingredient")
public class IngredientRestController {

    @Autowired
    IngredientService service;

    @GetMapping
    public List<Ingredient> findAll() {
        return service.findAll();
    }
    @GetMapping("/{id}")
    public Ingredient findById(@PathVariable int id) {
        return service.findById(id).get();
    }

    @PostMapping()
    public Ingredient save(@RequestBody Ingredient ingredient) {
        return service.save(ingredient);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.deleteById(id);
    }

    @PutMapping("")
    public Ingredient update(@RequestBody Ingredient ingredient) {
        return service.update(ingredient);
    }
}
