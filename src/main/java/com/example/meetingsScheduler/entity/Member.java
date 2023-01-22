package com.example.meetingsScheduler.entity;



import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    @OneToMany
    private Set<MeetingMember> meetingsList = new HashSet<>();

}