package com.example.RunningLifeServer.repository;

import com.example.RunningLifeServer.domain.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayRepository extends JpaRepository<Day, Long> {
}