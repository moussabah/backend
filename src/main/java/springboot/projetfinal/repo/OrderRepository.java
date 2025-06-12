package springboot.projetfinal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.projetfinal.enums.Status;
import springboot.projetfinal.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

//    List<Order> findAllByOrderByOrderNumberAsc();
//    Order findByOrderNumber(String orderNumber);
//    List<Order> findByStatus(Status status);
//    List<Order> findAllByOrderByOrderDateAsc();
//    List<Order> findAllByOrder_Lines(String line);
//
//    List<Order> findAllByOrder_Customer_FirstName(String firstName);
//    //List<Order> findAllByCustomer_FirstNameAndCustomer_LastName(String firstName, String lastName);
//    List<Order> findAllByOrder_Customer_Email(String email);
//    List<Order> findAllByOrder_Customer_PhoneNumber(String phoneNumber);

}
