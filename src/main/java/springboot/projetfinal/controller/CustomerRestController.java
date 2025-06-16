package springboot.projetfinal.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springboot.projetfinal.model.Customer;
import springboot.projetfinal.model.JsonViews;
import springboot.projetfinal.service.CustomerService;


import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
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

    @PostMapping("/login")
    @JsonView(JsonViews.CustomerWithAll.class)
    public Customer login(@RequestBody Map<String, String> credentials) {
        String login = credentials.get("login");
        String password = credentials.get("password");
        return service.login(login, password);
    }

    @PostMapping("/uploadPhoto")
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file) {
        return service.uploadCustomerPhoto(file);
    }

    @GetMapping("/loadPhoto/{filename}")
    public ResponseEntity<byte[]> loadPhoto(@PathVariable String filename) {
        return service.loadCustomerPhoto(filename);
    }

    /**
     * ✅ Nouveau endpoint enrichi : Customer + Orders + OrderLines
     */
    @GetMapping("/withOrdersLines")
    @JsonView(JsonViews.CustomerWithOrdersWithOrderLines.class)
    public List<Customer> getCustomersWithOrdersAndOrderLines() {
        return service.findAll();
    }
}
