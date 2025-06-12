package springboot.projetfinal.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.projetfinal.model.Address;
import springboot.projetfinal.model.JsonViews;
import springboot.projetfinal.service.AddressService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/addresses")
public class AddressRestController {

    @Autowired
    AddressService service;

    @GetMapping
    @JsonView(JsonViews.Common.class)
    public List<Address> findAll() {
        return service.findAll();
    }
    @GetMapping("/{id}")
    public Address findById(@PathVariable int id) {
        return service.findById(id).get();
    }

    @PostMapping()
    public Address save(@RequestBody Address address) {
        return service.save(address);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.deleteById(id);
    }

    @PutMapping("")
    public Address update(@RequestBody Address address) {
        return service.update(address);
    }
}
