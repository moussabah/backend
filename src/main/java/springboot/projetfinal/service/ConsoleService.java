package springboot.projetfinal.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import springboot.projetfinal.model.Address;
import springboot.projetfinal.model.Customer;
import springboot.projetfinal.repo.AddressRepository;
import springboot.projetfinal.repo.CustomerRepository;

import java.util.List;

@Service
public class ConsoleService implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    // Créer un client avec des adresses
    @Transactional
    public Customer createCustomerWithAddresses(String firstName, String lastName, List<Address> addresses) {
        Customer customer = new Customer();
        customer.setFirstname(firstName);
        customer.setLastname(lastName);
        for (Address address : addresses) {
            customer.addAddress(address);
        }

        return customerRepository.save(customer);
    }

    // Ajouter une adresse à un client existant
    @Transactional
    public Customer addAddressToCustomer(Integer customerId, Address address) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.addAddress(address);
        customerRepository.save(customer);
        return customer;
    }

    public void testInsert() {
        // Créer des adresses
        Address address1 = new Address("123 Main St", "Springfield", "12345", null, 10, "USA");
        Address address2 = new Address("456 Oak Ave", "Shelbyville", "67890", null, 15, "USA");

        // Créer un client
        Customer customer = new Customer();
        customer.setFirstname("John");
        customer.setLastname("Doe");

        // Ajouter les adresses au client
        customer.addAddress(address1);
        customer.addAddress(address2);

        // Associer le client aux adresses
        address1.setCustomer(customer);
        address2.setCustomer(customer);

        // Sauvegarder le client et ses adresses dans la base de données
        customerRepository.save(customer);
    }



    @Override
    public void run(String... args) throws Exception {
        testInsert();
    }
}
