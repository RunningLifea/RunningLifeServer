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
    public void signUp(@RequestBody Map<String, Object> map) {
        User user = new User();
        user.setName(map.get("name").toString());
        user.setDistance((Integer) map.get("distance"));
        user.setTime((Integer) map.get("time"));
        userService.signUp(user);
    }

    @GetMapping("/findByName")
    public Optional<User> findByName(@RequestParam String name) {
        return userService.findByName(name);
    }

    @PostMapping("/update")
    public void update(@RequestBody Map<String, Object> map, @RequestParam String name) {
        User user = new User();
        user.setName(map.get("name").toString());
        user.setDistance((Integer) map.get("distance"));
        user.setTime((Integer) map.get("time"));

        userService.update(user, name);
    }
}
