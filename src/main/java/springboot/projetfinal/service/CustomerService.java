package springboot.projetfinal.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import springboot.projetfinal.model.*;
import springboot.projetfinal.repo.CustomerRepository;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    // 🔐 Login sécurisé
    @Transactional
    public String loginAndReturnJwt(String login, String rawPassword) {
        Customer customer = repository.findByLogin(login)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants invalides"));

        if (!passwordEncoder.matches(rawPassword, customer.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants invalides");
        }

        return jwtService.generateToken(customer.getLogin());
    }

    @Transactional
    public Customer login(String login, String password) {
        return repository.findByLoginAndPassword(login, password)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants invalides"));
    }


    // 📝 Inscription avec vérification du doublon
    @Transactional
    public Customer register(String login, String rawPassword) {
        if (repository.findByLogin(login).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Login déjà utilisé");
        }
        String hashedPassword = passwordEncoder.encode(rawPassword);
        Customer customer = new Customer(login, hashedPassword);
        return repository.save(customer);
    }

    // 🔍 Récupération client
    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Optional<Customer> findById(int id) {
        return repository.findById(id);
    }

    // 💾 Sauvegarde et mise à jour
    @Transactional
    public Customer save(Customer customer) {
        prepareCustomerForSaveOrUpdate(customer);
        return repository.save(customer);
    }

    @Transactional
    public Customer update(Customer customer) {
        prepareCustomerForSaveOrUpdate(customer);
        return repository.save(customer);
    }

    // 🔧 Préparation des objets liés
    private void prepareCustomerForSaveOrUpdate(Customer customer) {
        if (customer.getPassword() != null) {
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        }

        if (customer.getAddresses() != null) {
            for (Address address : customer.getAddresses()) {
                address.setCustomer(customer);
            }
        }
        if (customer.getOrders() != null) {
            for (Order order : customer.getOrders()) {
                order.setCustomer(customer);
            }
        }
        if (customer.getReservations() != null) {
            for (Reservation reservation : customer.getReservations()) {
                reservation.setCustomer(customer);
            }
        }
    }

    // ❌ Suppression
    @Transactional
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    // 🏠 Ajouter une adresse à un client
    @Transactional
    public Customer addAddressToCustomer(Integer customerId, Address address) {
        Customer customer = repository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client non trouvé"));

        customer.addAddress(address);
        return repository.save(customer);
    }

    // 🖼️ Upload d'image
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'upload");
        }
    }

    // 🖼️ Chargement d'image
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
