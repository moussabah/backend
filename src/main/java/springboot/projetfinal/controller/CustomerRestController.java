package springboot.projetfinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.projetfinal.model.Customer;
import springboot.projetfinal.service.CustomerService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerRestController {

    @Autowired
    CustomerService service;

    @GetMapping
    public List<Customer> findAll() {
        return service.findAll();
    }
    @GetMapping("/{id}")
    public Customer findById(@PathVariable int id) {
        return service.findById(id).get();
    }

    @PostMapping()
    public Customer save(@RequestBody Customer customer) {
        return service.save(customer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.deleteById(id);
    }

    @PutMapping("")
    public Customer update(@RequestBody Customer customer) {
        return service.update(customer);
    }
}
