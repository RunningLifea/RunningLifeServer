package com.example.RunningLifeServer.repository;

import com.example.RunningLifeServer.domain.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DayRepository extends JpaRepository<Day, Long> {

    @Query("SELECT d FROM Day d WHERE d.user.id = any(SELECT u.id FROM User u WHERE u.name = :user_name) and d.date = :date")
    List<Day> findAllByDateAndUserName(@Param("user_name") String user_name, @Param("date") LocalDate date);

    @Query("SELECT d FROM Day d WHERE d.user.id = any(SELECT u.id FROM User u WHERE u.name = :user_name) and d.date = :date and d.start = :start")
    Optional<Day> findDayByDateAndUserNameAndStart(@Param("user_name") String user_name, @Param("date") LocalDate date, @Param("start") LocalTime start);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Day d SET d.start = :start, d.end = :end, d.location = :location  WHERE d.id = :older_day_id")
    void updateDay(@Param("older_day_id")Long older_day_id, @Param("start") LocalTime start, @Param("end") LocalTime end, @Param("location") String location);
}