package springboot.projetfinal.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.projetfinal.model.Item;
import springboot.projetfinal.model.JsonViews;
import springboot.projetfinal.service.ItemService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/items")
public class ItemRestController {

    @Autowired
    ItemService service;

    @GetMapping("")
    @JsonView(JsonViews.Common.class)
    public List<Item> findAll() {
        return service.findAll();
    }
    @GetMapping("/{id}")
    public Item findById(@PathVariable int id) {
        return service.findById(id).get();
    }

    @PostMapping()
    public Item save(@RequestBody Item item) {
        return service.save(item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.deleteById(id);
    }

    @PutMapping("")
    public Item update(@RequestBody Item item) {
        return service.update(item);
    }
}
