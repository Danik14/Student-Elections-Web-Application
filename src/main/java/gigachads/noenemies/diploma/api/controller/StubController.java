package gigachads.noenemies.diploma.api.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StubController {
    @GetMapping("/1")
    public String stub(Authentication user) {
        return "kamal chmok";
    }
}
