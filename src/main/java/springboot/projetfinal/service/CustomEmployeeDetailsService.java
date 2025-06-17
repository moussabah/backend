package springboot.projetfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springboot.projetfinal.model.Employee;
import springboot.projetfinal.repo.EmployeeRepository;

import java.util.List;

@Service
public class CustomEmployeeDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Employee not found"));

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(employee.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(
                employee.getLogin(),
                employee.getPassword(),
                employee.isActive(),
                true, true, true,
                authorities
        );
    }
}

