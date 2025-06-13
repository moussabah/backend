package springboot.projetfinal.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.projetfinal.model.Order;
import springboot.projetfinal.repo.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Optional<Order> findById(int id) {
        return repository.findById(id);
    }

    public Order save(Order order) {
        return repository.save(order);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
    public Order update(Order order) {
        return repository.save(order);
    }
//
//    public List<Order> findAllByOrderByOrderNumberAsc(){
//        return repository.findAllByOrderByOrderNumberAsc();
//    }
//
//    public Order findByOrderNumber(String orderNumber){
//        return repository.findByOrderNumber(orderNumber);
//    }
//
//    public List<Order> findAllByOrder_Lines(String line){
//        return repository.findAllByOrder_Lines(line);
//    }
//
//    public List<Order> findByStatus(Status status){
//        return repository.findByStatus(status);
//    }
//
//    public List<Order> findAllByOrderByOrderDateAsc(){
//        return repository.findAllByOrderByOrderDateAsc();
//    }
//
//    public List<Order> findAllByOrder_Customer_FirstName(String firstName){
//        return repository.findAllByOrder_Customer_FirstName(firstName);
//    }
//    public List<Order> findAllByOrder_Customer_FirstNameAndLastName(String firstName, String lastName){
//        return repository.findAllByOrder_Customer_FirstNameAndLastName(firstName, lastName);
//    }
//    public List<Order> findAllByOrder_Customer_Email(String email){
//        return repository.findAllByOrder_Customer_Email(email);
//    }
//
//    public List<Order> findAllByOrder_Customer_PhoneNumber(String phoneNumber){
//        return repository.findAllByOrder_Customer_PhoneNumber(phoneNumber);
    //}

}
