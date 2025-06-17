package springboot.projetfinal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.projetfinal.model.Employee;

import java.util.Optional;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByLogin(String login);
}
