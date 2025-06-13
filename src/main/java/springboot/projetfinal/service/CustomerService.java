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

    @Transactional
    public Customer addAddressToCustomer(Integer customerId, Address address) {
        return repository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Transactional
    public Customer createCustomerWithAddresses(String firstName, String lastName, List<Address> addresses) {
        Customer customer = new Customer();
        customer.setFirstname(firstName);
        customer.setLastname(lastName);
        for (Address address : addresses) {
            customer.addAddress(address);
        }
        return customer;
    }

    @Transactional
    public Customer createCustomerWithAddress(String firstName, String lastName, Address address) {
        Customer customer = new Customer();
        customer.setFirstname(firstName);
        customer.setLastname(lastName);
        return customer;
    }
}
