package springboot.projetfinal.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import springboot.projetfinal.model.Address;
import springboot.projetfinal.model.Customer;
import springboot.projetfinal.model.Order;
import springboot.projetfinal.model.Reservation;
import springboot.projetfinal.repo.CustomerRepository;


import java.util.List;
import java.util.Optional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
    @Transactional
    public Customer save(Customer customer) {
        if (customer.getAddresses() != null) {
            for (Address address : customer.getAddresses()) {
                address.setCustomer(customer); // 🟢 Réassocier le lien
            }
        }
        if (customer.getOrders() != null) {
            for (Order order : customer.getOrders()) {
                order.setCustomer(customer); // 🟢 Réassocier le lien
            }
        }
        if (customer.getReservations() != null) {
            for (Reservation reservation : customer.getReservations()) {
                reservation.setCustomer(customer); // 🟢 Réassocier le lien
            }
        }

        return repository.save(customer);
    }
    @Transactional
    public void deleteById(int id) {
        repository.deleteById(id);
    }
    @Transactional
    public Customer update(Customer customer) {
        if (customer.getAddresses() != null) {
            for (Address address : customer.getAddresses()) {
                address.setCustomer(customer); // 🟢 Réassocier le lien
            }
        }
        if (customer.getOrders() != null) {
            for (Order order : customer.getOrders()) {
                order.setCustomer(customer); // 🟢 Réassocier le lien
            }
        }
        if (customer.getReservations() != null) {
            for (Reservation reservation : customer.getReservations()) {
                reservation.setCustomer(customer); // 🟢 Réassocier le lien
            }
        }

        return repository.save(customer); // ou .saveAndFlush
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

    @Transactional
    public Customer login(String login, String password) {
        return repository.findByLoginAndPassword(login, password)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants invalides"));
    }

    @Transactional
    public ResponseEntity<String> uploadCustomerPhoto(MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Fichier vide");
        }

        String uploadDir = "src/main/resources/static/images/customers/";

        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return ResponseEntity.ok(file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'upload");
        }
    }


    @Transactional
    public ResponseEntity<byte[]> loadCustomerPhoto(String filename) {
        String uploadDir = "src/main/resources/static/images/customers/";
        Path filePath = Paths.get(uploadDir).resolve(filename);

        try {
            byte[] imageBytes = Files.readAllBytes(filePath);
            String contentType = Files.probeContentType(filePath);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(contentType != null ? contentType : "application/octet-stream"));

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
