package com.example.meetingsScheduler.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class MeetingAgenda extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Meeting meetingId;

}
