package gigachads.noenemies.diploma.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/loginsuccess")
    public String log3() {
        return "loginsuccess";
    }

    @GetMapping("/logoutsuccess")
    public String log2() {
        return "logoutsuccess";
    }

}
