package springboot.projetfinal.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springboot.projetfinal.model.Order;
import springboot.projetfinal.model.Reservation;
import springboot.projetfinal.repo.ReservationRepository;

@Controller
@RequestMapping("/mvcReservations")
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        return new ModelAndView("reservation/detail", "reservation", reservation);
    }
}
