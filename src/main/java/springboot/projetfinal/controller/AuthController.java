package springboot.projetfinal.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.projetfinal.model.Customer;
import springboot.projetfinal.repo.CustomerRepository;
import springboot.projetfinal.service.JwtService;
import springboot.projetfinal.service.TokenBlacklistService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtService jwtService;
    @Autowired
    CustomerRepository repo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    TokenBlacklistService blacklist;

    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody Map<String,String> cred) {
        String login = cred.get("login");
        String password = cred.get("password");
        System.out.println("Tentative de connexion : " + login);

        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login, password)
            );
        } catch (Exception ex) {
            ex.printStackTrace();  // Ajoute ceci pour voir le détail de l'erreur dans la console
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants invalides");
        }

        String token = jwtService.generateToken(login);
        return ResponseEntity.ok(Map.of("token", token));
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String,String> data) {
        if (repo.findByLogin(data.get("login")).isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Login déjà utilisé");

        Customer c = new Customer();
        c.setLogin(data.get("login"));
        c.setPassword(passwordEncoder.encode(data.get("password")));
        c.setFirstname(data.get("firstname"));
        c.setLastname(data.get("lastname"));
        repo.save(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest req) {
        String h = req.getHeader("Authorization");
        if (h != null && h.startsWith("Bearer ")) {
            String token = h.substring(7);
            blacklist.blacklist(token, 3600);
        }
        return ResponseEntity.ok(Map.of("msg", "Déconnecté"));
    }
}
