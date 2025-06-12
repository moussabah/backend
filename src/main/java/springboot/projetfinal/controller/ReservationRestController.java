package springboot.projetfinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.projetfinal.model.Reservation;
import springboot.projetfinal.service.ReservationService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/reservation")
public class ReservationRestController {

    @Autowired
    ReservationService service;

    @GetMapping
    public List<Reservation> findAll() {
        return service.findAll();
    }
    @GetMapping("/{id}")
    public Reservation findById(@PathVariable int id) {
        return service.findById(id).get();
    }

    @PostMapping()
    public Reservation save(@RequestBody Reservation reservation) {
        return service.save(reservation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.deleteById(id);
    }

    @PutMapping("")
    public Reservation update(@RequestBody Reservation reservation) {
        return service.update(reservation);
    }
}
