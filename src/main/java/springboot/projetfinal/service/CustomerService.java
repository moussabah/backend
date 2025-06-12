package springboot.projetfinal.service;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.projetfinal.model.Address;
import springboot.projetfinal.model.Customer;
import springboot.projetfinal.repo.AddressRepository;
import springboot.projetfinal.repo.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Optional<Customer> findById(int id) {
        return repository.findById(id);
    }

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
    public Customer update(Customer customer) {
        return repository.save(customer);
    }

}
