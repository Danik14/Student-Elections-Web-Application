package gigachads.noenemies.diploma.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@RestController
public class HomeController {
    @GetMapping("/hello")
    public Object hello(Principal principal) {
        System.out.println(principal);
        return principal;
    }

    @GetMapping("/login")
    public RedirectView login() {
        return new RedirectView("http://localhost:8080/oauth2/authorization/azure");
    }
}
