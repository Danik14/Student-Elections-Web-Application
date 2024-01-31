package gigachads.noenemies.diploma.api.controller;

import gigachads.noenemies.diploma.api.dto.UserResponse;
import gigachads.noenemies.diploma.domain.mapper.UserMapper;
import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

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

    @GetMapping("/{userId}")
    public UserResponse getUserById(@PathVariable UserId userId) {
        return userMapper.toResponse(userService.getUserById(userId));
    }

    @GetMapping("/barcode/{barcode}")
    public UserResponse getUserById(@PathVariable String barcode) {
        return userMapper.toResponse(userService.getUserByBarcode(barcode));
    }

    @GetMapping("/{userId}/candidates")
    public List<UserResponse> getAllCandidates() {
        return userMapper.toResponse(userService.getAllCandidates());
    }

    @GetMapping("/{userId}/candidates/active")
    public List<UserResponse> getAllActiveCandidates() {
        return userMapper.toResponse(userService.getAllActiveCandidates());
    }
}