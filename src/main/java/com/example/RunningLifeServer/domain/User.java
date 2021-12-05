package com.example.RunningLifeServer.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB 가 알아서 생성
    private Long id;
    private String name;
    private int distance;
    private int time;

}
