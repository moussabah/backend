package springboot.projetfinal.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.projetfinal.model.JsonViews;
import springboot.projetfinal.model.Order;
import springboot.projetfinal.service.OrderService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderRestController {

    @Autowired
    OrderService service;

    @GetMapping("")
    @JsonView(JsonViews.Common.class)
    public List<Order> findAll() {
        return service.findAll();
    }
    @GetMapping("/{id}")
    public Order findById(@PathVariable int id) {
        return service.findById(id).get();
    }

    @PostMapping()
    public Order save(@RequestBody Order order) {
        return service.save(order);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.deleteById(id);
    }

    @PutMapping("")
    public Order update(@RequestBody Order order) {
        return service.update(order);
    }

//    @GetMapping("/orderNumber/{orderNumber}")
//    public Order findByOrderNumber(@PathVariable String orderNumber){
//        return service.findByOrderNumber(orderNumber);
//    }
//    @GetMapping("/orderLines/{line}")
//    public List<Order> findAllByOrder_Lines(@PathVariable String line){
//        return service.findAllByOrder_Lines(line);
//    }
//
//    @GetMapping("/status/{status}")
//    public List<Order> findAllByStatus(@PathVariable Status status){
//        return service.findByStatus(status);
//    }
//
//    @GetMapping("/orderDateAsc")
//    public List<Order> findAllByOrderByOrderDateAsc(){
//        return service.findAllByOrderByOrderDateAsc();
//    }
//    @GetMapping("/customerFirstName/{firstName}")
//    public List<Order> findAllByOrder_Customer_FirstName(@PathVariable String firstName){
//        return service.findAllByOrder_Customer_FirstName(firstName);
//    }
//
//    @GetMapping("/customer/{firstName}/{lastName}")
//    public List<Order> findAllByOrder_Customer_FirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName){
//        return service.findAllByOrder_Customer_FirstNameAndLastName(firstName, lastName);
//    }
//    @GetMapping("/customerEmail/{email}")
//    public List<Order> findAllByOrder_Customer_Email(@PathVariable String email){
//        return service.findAllByOrder_Customer_Email(email);
//    }
//
//    @GetMapping("/customerPhoneNumber/{phoneNumber}")
//    public List<Order> findAllByOrder_Customer_PhoneNumber(@PathVariable String phoneNumber){
//        return service.findAllByOrder_Customer_PhoneNumber(phoneNumber);
//    }

}
