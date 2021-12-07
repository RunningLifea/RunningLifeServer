package com.example.RunningLifeServer.service;

import com.example.RunningLifeServer.domain.Record;
import com.example.RunningLifeServer.domain.User;
import com.example.RunningLifeServer.repository.DayRepository;
import com.example.RunningLifeServer.repository.RecordRepository;
import com.example.RunningLifeServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final UserRepository userRepository;

    public void upload(Record record,  String name) {
        User user = userRepository.findByName(name).get();
        record.setUser(user);

        recordRepository.save(record);
    }

    public Optional<Record> findByNameAndDate(String name, LocalDate date) {
        return recordRepository.findByDateAndUserName(name, date);
    }

    public List<Record> findByDateBetweenAndUserName(String name, String start_date, String end_date) {
        return recordRepository.findByDateBetweenAndUserName(name, start_date, end_date);
    }

}
