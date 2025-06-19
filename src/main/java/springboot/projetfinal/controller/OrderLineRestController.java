package springboot.projetfinal.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.projetfinal.model.JsonViews;
import springboot.projetfinal.model.OrderLine;
import springboot.projetfinal.service.OrderLineService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/order_lines")
public class OrderLineRestController {

    @Autowired
    OrderLineService service;

    @GetMapping("")
    @JsonView(JsonViews.OrderLineWithAll.class)
    public List<OrderLine> findAll() {
        return service.findAll();
    }

    @JsonView({JsonViews.OrderLineWithAll.class})
    @GetMapping("/{id}")
    public OrderLine findById(@PathVariable int id) {
        return service.findById(id).get();
    }


    @PostMapping("")
    public OrderLine save(@RequestBody OrderLine line) {
        return service.save(line);
    }
    @JsonView({JsonViews.OrderLineWithAll.class})
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.deleteById(id);
    }

    @JsonView({JsonViews.OrderLineWithAll.class})
    @PutMapping("")
    public OrderLine update(@RequestBody OrderLine line) {
        return service.update(line);
    }
}