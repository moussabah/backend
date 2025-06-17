package springboot.projetfinal.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // renvoie à login.jsp
    }

    @GetMapping("/logout-success")
    public String logoutPage() {
        return "logout"; // page optionnelle après déconnexion
    }
}
