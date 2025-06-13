package springboot.projetfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.projetfinal.model.Address;
import springboot.projetfinal.repo.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    public List<Address> findAll() {
        return repository.findAll();
    }

    public Optional<Address> findById(int id) {
        return repository.findById(id);
    }

    public Address save(Address address) {
        return repository.save(address);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
    public Address update(Address address) {
        return repository.save(address);
    }
}
