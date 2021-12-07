package com.example.RunningLifeServer.service;

import com.example.RunningLifeServer.domain.Day;
import com.example.RunningLifeServer.repository.DayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DayService {

    private final DayRepository dayRepository;

    public Day upload(Day day){
        checkValidation(day);
        Day new_day = dayRepository.save(day);
        return new_day;
    }

    private void checkValidation(Day day) {
        dayRepository.findDayByDateAndUserNameAndStart(day.getUser().getName(), day.getDate(), day.getStart().toString(), day.getEnd().toString()).ifPresent(
                m-> {
                    throw new IllegalStateException("existing schedule");
                }
        );
    }

    public Optional<Day> findDayByDateAndUserNameAndStart(Day day) {
        Optional<Day> result =  dayRepository.findDayByDateAndUserNameAndStart(day.getUser().getName(), day.getDate(), day.getStart().toString(),day.getEnd().toString());
        return result;
    }

    public List<Day> find(String name, String date) {
        return dayRepository.findAllByDateAndUserName(name, LocalDate.parse(date, DateTimeFormatter.ISO_DATE));
    }

    public Optional<Day> update(Day old_day, Day new_day) {
        Day older_day = findDayByDateAndUserNameAndStart(old_day).get();
        dayRepository.updateDay(older_day.getId(), new_day.getStart(), new_day.getEnd(), new_day.getLocation());
        return findDayByDateAndUserNameAndStart(new_day);
    }

    public Day complete(Day day, String name) {
        Optional<Day> target = dayRepository.findDayByDateAndUserNameAndStart(name, day.getDate(), day.getStart().toString(), day.getEnd().toString());
        Day new_day = target.get();
        new_day.setComplete(true);

        return dayRepository.save(new_day);
    }


}
