package com.example.RunningLifeServer.repository;

import com.example.RunningLifeServer.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    @Query(value = "SELECT * FROM record WHERE user_id = ANY (SELECT id FROM user WHERE name = :user_name) AND date = :date", nativeQuery = true)
    Optional<Record> findByDateAndUserName(@Param("user_name") String user_name, @Param("date")LocalDate date);

    @Query(value = "SELECT * FROM record WHERE user_id = ANY (SELECT id FROM user WHERE name = :user_name) And NOT(date > :end_date OR date < :start_date)", nativeQuery = true)
    List<Record> findByDateBetweenAndUserName(@Param("user_name") String user_name, @Param("start_date") String start_date, @Param("end_date") String end_date);
}