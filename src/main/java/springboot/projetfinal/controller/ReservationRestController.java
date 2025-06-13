package springboot.projetfinal.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.projetfinal.model.JsonViews;
import springboot.projetfinal.model.Reservation;
import springboot.projetfinal.service.ReservationService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/reservations")
public class ReservationRestController {

    @Autowired
    ReservationService service;

    @GetMapping("")
    @JsonView(JsonViews.ReservationWithAll.class)
    public List<Reservation> findAll() {
        return service.findAll();
    }
    @GetMapping("/{id}")
    @JsonView(JsonViews.ReservationWithAll.class)
    public Reservation findById(@PathVariable int id) {
        return service.findById(id).get();
    }

    @PostMapping("")
    @JsonView(JsonViews.ReservationWithAll.class)
    public Reservation save(@RequestBody Reservation reservation) {
        return service.save(reservation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.deleteById(id);
    }

    @PutMapping("")
    @JsonView(JsonViews.ReservationWithAll.class)
    public Reservation update(@RequestBody Reservation reservation) {
        return service.update(reservation);
    }
}
