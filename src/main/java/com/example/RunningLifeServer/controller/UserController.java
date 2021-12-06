package com.example.RunningLifeServer.controller;

import com.example.RunningLifeServer.domain.User;
import com.example.RunningLifeServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public void signUp(@RequestBody User user) {
        userService.signUp(user);
    }

    @GetMapping("/findByName")
    public Optional<User> findByName(@RequestParam String name) {
        return userService.findByName(name);
    }

    @PostMapping("/update")
    public void update(@RequestBody User user, @RequestParam String name) {
        userService.update(user, name);
    }
}
