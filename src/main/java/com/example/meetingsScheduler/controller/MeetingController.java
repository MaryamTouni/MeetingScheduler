package com.example.meetingsScheduler.controller;

import com.example.meetingsScheduler.model.Meeting;
import com.example.meetingsScheduler.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

    @Autowired
    MeetingService meetingService;
    @GetMapping("/list")
    public List<Meeting> listMeeting(){
        return meetingService.getAll();
    }

    @DeleteMapping("/delete/{meetingId}")
    public void deleteMeeting(@PathVariable Long meetingId){
        meetingService.deleteMeeting(meetingId);
    }

    @PutMapping("/update")
    public Meeting updateMeeting(@RequestBody Meeting model){
        return meetingService.updateMeeting(model);
    }

    @PostMapping("/create")
    public Meeting createMeeting(@RequestBody Meeting model){
        return meetingService.createMeeting(model);
    }
}
