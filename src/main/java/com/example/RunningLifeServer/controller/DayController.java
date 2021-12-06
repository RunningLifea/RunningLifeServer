package com.example.RunningLifeServer.controller;

import com.example.RunningLifeServer.domain.Day;
import com.example.RunningLifeServer.domain.User;
import com.example.RunningLifeServer.service.DayService;
import com.example.RunningLifeServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/day")
@RequiredArgsConstructor
public class DayController {

    private final DayService dayService;
    private final UserService userService;

    @PostMapping("/upload")
    public Day upload(@RequestBody Day day, @RequestParam String name) {
        User user = userService.findByName(name).get();
        day.setUser(user);
        return dayService.upload(day);
    }

    @GetMapping("/find")
    public List<Day> find(@RequestParam String name, @RequestParam String date) {
        return dayService.find(name, date);
    }

    @PostMapping("/update")
    public Optional<Day> update(@RequestBody Day old_day, @RequestBody Day new_day, @RequestParam String name) {
        old_day.setUser(new User(name));
        return dayService.update(old_day, new_day);
    }


}
