package com.example.RunningLifeServer.controller;

import com.example.RunningLifeServer.domain.Record;
import com.example.RunningLifeServer.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @PostMapping("/upload")
    public void upload(@RequestBody Record record, @RequestParam String name) {

        Optional<Record> find_record = recordService.findByNameAndDate(name, record.getDate());

        if (find_record.isPresent()) {
            Record oringin_record = find_record.get();
            oringin_record.setDistance(oringin_record.getDistance() + record.getDistance());
            recordService.upload(oringin_record, name);
        } else {
            recordService.upload(record, name);
        }
    }

    @GetMapping("/month")
    public List<Record> month(@RequestParam String name, @RequestParam String start_date, @RequestParam String end_date) {

        return recordService.findByDateBetweenAndUserName(name, start_date, end_date);
    }
}
