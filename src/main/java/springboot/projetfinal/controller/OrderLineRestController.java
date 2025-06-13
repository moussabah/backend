package springboot.projetfinal.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.projetfinal.model.JsonViews;
import springboot.projetfinal.model.OrderLine;
import springboot.projetfinal.service.OrderLineService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/order_lines")
public class OrderLineRestController {

    @Autowired
    OrderLineService service;

    @GetMapping("")
    @JsonView(JsonViews.OrderWithAll.class)
    public List<OrderLine> findAll() {
        return service.findAll();
    }

    @JsonView({JsonViews.OrderWithAll.class})
    @GetMapping("/{id}")
    public OrderLine findById(@PathVariable int id) {
        return service.findById(id).get();
    }

    @PostMapping("")
    public OrderLine save(@RequestBody OrderLine line) {
        return service.save(line);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.deleteById(id);
    }

    @PutMapping("")
    public OrderLine update(@RequestBody OrderLine line) {
        return service.update(line);
    }
}