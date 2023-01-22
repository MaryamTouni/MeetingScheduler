package com.example.meetingsScheduler.model;


import com.example.meetingsScheduler.entity.Role;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Member {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private Set<Meeting> meetingsList = new HashSet<>();

}
