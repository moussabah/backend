package springboot.projetfinal.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springboot.projetfinal.model.Order;
import springboot.projetfinal.repo.OrderRepository;

@Controller
@RequestMapping("/mvcOrders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable int id) {
        Order order = orderRepository.findById(id).orElseThrow();
        return new ModelAndView("order/detail", "order", order);
    }
}
