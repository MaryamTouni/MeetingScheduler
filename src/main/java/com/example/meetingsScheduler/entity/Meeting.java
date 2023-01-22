package com.example.meetingsScheduler.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Meeting extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;
    private String subject;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDate meetingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    @OneToMany
    private Set<MeetingAgenda> meetingAgendaSet = new HashSet<MeetingAgenda>();
    @OneToMany
    private Set<MeetingMember> meetingMemberSet = new HashSet<MeetingMember>();
}
