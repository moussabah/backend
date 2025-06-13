package springboot.projetfinal.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.projetfinal.model.Ingredient;
import springboot.projetfinal.model.JsonViews;
import springboot.projetfinal.service.IngredientService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ingredients")
public class IngredientRestController {

    @Autowired
    IngredientService service;

    @GetMapping("")
    @JsonView(JsonViews.IngredientWithAll.class)
    public List<Ingredient> findAll() {
        return service.findAll();
    }
    @GetMapping("/{id}")
    @JsonView(JsonViews.IngredientWithAll.class)
    public Ingredient findById(@PathVariable int id) {
        return service.findById(id).get();
    }

    @PostMapping("")
    @JsonView(JsonViews.IngredientWithAll.class)
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

    @GetMapping("findbyname/{name}")
    public Ingredient findByName(@PathVariable String name){
        return service.findAll().stream().filter(i -> i.getName().equals(name)).findFirst().get();
    }
}
