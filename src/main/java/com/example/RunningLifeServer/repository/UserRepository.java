package com.example.RunningLifeServer.repository;

import com.example.RunningLifeServer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.name = :updateName, u.distance = :updateDistance, u.time = :updateTime WHERE u.name = :name")
    void updateUser(String updateName,int updateDistance, int updateTime, String name);
}