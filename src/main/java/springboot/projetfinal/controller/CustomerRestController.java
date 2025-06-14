package springboot.projetfinal.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.projetfinal.model.Customer;
import springboot.projetfinal.model.JsonViews;
import springboot.projetfinal.service.CustomerService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/customers")
public class CustomerRestController {

    @Autowired
    CustomerService service;

    @GetMapping("")
    @JsonView(JsonViews.CustomerWithAll.class)
    public List<Customer> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(JsonViews.CustomerWithAll.class)
    public Customer findById(@PathVariable int id) {
        return service.findById(id).get();
    }

    @PostMapping("")
    @JsonView(JsonViews.CustomerWithAll.class)
    public Customer save(@RequestBody Customer customer) {
        return service.save(customer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.deleteById(id);
    }

    @PutMapping("")
    @JsonView(JsonViews.CustomerWithAll.class)
    public Customer update(@RequestBody Customer customer) {
        return service.update(customer);
    }
}
