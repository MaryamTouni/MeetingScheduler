package com.example.meetingsScheduler.controller;


import com.example.meetingsScheduler.model.Member;
import com.example.meetingsScheduler.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/list")
    public List<Member> list(){
        return memberService.listAllMembers();
    }

    @DeleteMapping("/delete/{memberId}")
    public void deleteMember(@PathVariable Long memberId){
        memberService.deleteMember(memberId);
    }

    @PutMapping("/update")
    public Member updateMember(@RequestBody Member model){
        return memberService.updateMember(model);
    }

    @PostMapping("/create")
    public Member createMember(@RequestBody Member model){
        return memberService.createMember(model);
    }
}

