package springboot.projetfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springboot.projetfinal.model.Customer;
import springboot.projetfinal.repo.CustomerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository repo;
    @Override
    public UserDetails loadUserByUsername(String login) {
        Customer c = repo.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
        return User.builder()
                .username(c.getLogin())
                .password(c.getPassword())
                .roles("CUSTOMER")
                .build();
    }
}
