package springboot.projetfinal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.projetfinal.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
