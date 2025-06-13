package springboot.projetfinal.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    @JsonView(JsonViews.AddressWithAll.class)
    public List<Address> findAll() {
        return service.findAll();
    }
    @GetMapping("/{id}")
    @JsonView(JsonViews.AddressWithAll.class)
    public Address findById(@PathVariable int id) {
        return service.findById(id).get();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(JsonViews.AddressWithAll.class)
    public Address save(@RequestBody Address address, HttpServletRequest request) {
        System.out.println("Content-Type reçu : " + request.getContentType());
        return service.save(address);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.deleteById(id);
    }

    @PutMapping("")
    @JsonView(JsonViews.AddressWithAll.class)
    public Address update(@RequestBody Address address) {
        return service.update(address);
    }
}
