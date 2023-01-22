package com.example.meetingsScheduler.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class MeetingMember extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne
    private Meeting meeting;
    @ManyToOne
    private Member member;

}
