package com.example.meetingsScheduler.model;


import jakarta.annotation.PostConstruct;
import lombok.Data;
import com.example.meetingsScheduler.entity.Status;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class Meeting {

    private Long id;
    private String subject;
    private Status status;
    private LocalDate meetingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Set<Member> memberSet = new HashSet<Member>();
    private Set<Agenda> agendaSet = new HashSet<Agenda>();

}
