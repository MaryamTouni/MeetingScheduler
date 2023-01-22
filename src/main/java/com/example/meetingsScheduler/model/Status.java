package com.example.meetingsScheduler.model;

public enum Status {
    ACCEPTED("Accepted"),
    INPROGRESS("In progress"),
    CANCELED("Canceled");

    public final String label;

    private Status(String label) {
        this.label = label;
    }
}
