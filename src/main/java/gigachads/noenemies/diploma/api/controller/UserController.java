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

//https://login.microsoftonline.com/158f15f3-83e0-4906-824c-69bdc50d9d61/oauth2/v2.0/authorize?response_type=code&client_id=f66ef411-1b58-4aa5-9b11-5eebc9e4d715&scope=openid%20profile%20email&state=K3A6tzgyHg9GXqjgeY_T5VBR3DdIkTF0vsG_VBZJylo%3D&redirect_uri=http://localhost:8080/api/v1/login/oauth2/code/&nonce=vl5scRzfb1hX3k3aBEih_eG7rCosuuAIenWzKshkfrc