package springboot.projetfinal.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.projetfinal.model.OrderLine;
import springboot.projetfinal.repo.OrderLineRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderLineService {

    @Autowired
    private OrderLineRepository repository;

    public List<OrderLine> findAll() {
        return repository.findAll();
    }

    public Optional<OrderLine> findById(int id) {
        return repository.findById(id);
    }

    public OrderLine save(OrderLine line) {
        return repository.save(line);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
    public OrderLine update(OrderLine line) {
        return repository.save(line);
    }
}
