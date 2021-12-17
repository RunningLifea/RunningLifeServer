package com.example.RunningLifeServer.controller;

import com.example.RunningLifeServer.domain.Day;
import com.example.RunningLifeServer.domain.User;
import com.example.RunningLifeServer.service.DayService;
import com.example.RunningLifeServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
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
        day.setComplete(false);
        return dayService.upload(day);
    }

    @GetMapping("/find")
    public List<Day> find(@RequestParam String name, @RequestParam String date) {
        return dayService.find(name, date);
    }

    @Transactional
    @PostMapping("/update")
    public Optional<Day> update(@RequestBody Map<String, Day> map, @RequestParam String name) {
        Day old_day = map.get("old_day");
        Day new_day = map.get("new_day");
        new_day.setDate(old_day.getDate());

        old_day.setUser(new User(name));
        new_day.setUser(new User(name));
        return dayService.update(old_day, new_day);
    }

    @Transactional
    @PostMapping("/complete")
    public Day complete(@RequestBody Day day, @RequestParam String name) {
        return dayService.complete(day, name);
    }

    @GetMapping("/temperature")
    public int temperature(@RequestParam String datetime) {
        int temp = (int) (Math.random() * (5 - (-5)) + 10);

        return temp;
    }


}
