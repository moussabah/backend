package springboot.projetfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import springboot.projetfinal.model.Authentification;
import springboot.projetfinal.repo.AuthentificationRepository;

@Service
public class AuthentificationService {

    @Autowired
    private AuthentificationRepository repository;

    @PostMapping
    public Authentification register(Authentification authentification) {
        return repository.save(authentification);
    }
}
