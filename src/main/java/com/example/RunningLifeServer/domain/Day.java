package com.example.RunningLifeServer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB 가 알아서 생성
    private Long id;
    private String title;
    private LocalTime start;
    private LocalTime end;
    private String location;
    private int temperature;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "USER_ID")
    private User user;

}
