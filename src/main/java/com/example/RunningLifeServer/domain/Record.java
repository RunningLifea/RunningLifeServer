package com.example.RunningLifeServer.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB 가 알아서 생성
    private Long id;
    private int distance;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "USER_ID")
    private User user;
}
