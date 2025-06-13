package springboot.projetfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.projetfinal.model.Reservation;
import springboot.projetfinal.repo.ReservationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repository;

    public List<Reservation> findAll() {
        return repository.findAll();
    }

    public Optional<Reservation> findById(int id) {
        return repository.findById(id);
    }

    public Reservation save(Reservation reservation) {
        return repository.save(reservation);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
    public Reservation update(Reservation reservation) {
        return repository.save(reservation);
    }
}
