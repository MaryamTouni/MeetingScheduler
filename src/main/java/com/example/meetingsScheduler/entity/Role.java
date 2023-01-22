package com.example.meetingsScheduler.entity;

public enum Role {
    A("10"),
    B("20"),
    C("30");

    public final String label;

    private Role(String label) {
        this.label = label;
    }
}
