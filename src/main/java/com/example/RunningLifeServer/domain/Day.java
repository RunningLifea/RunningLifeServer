package com.example.RunningLifeServer.domain;

import com.example.RunningLifeServer.converter.BooleanToYNConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB 가 알아서 생성
    private Long id;
    private String title;
    @JsonFormat(pattern = "kk:mm")
    private LocalTime start;
    @JsonFormat(pattern = "kk:mm")
    private LocalTime end;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String location;
    private int temperature;
    @Convert(converter = BooleanToYNConverter.class)
    private boolean complete;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "USER_ID")
    private User user;


}
