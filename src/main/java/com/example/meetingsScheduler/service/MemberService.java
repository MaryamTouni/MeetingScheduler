package com.example.meetingsScheduler.service;

import com.example.meetingsScheduler.entity.MeetingMember;
import com.example.meetingsScheduler.model.Meeting;
import com.example.meetingsScheduler.model.Member;
import com.example.meetingsScheduler.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public List<Member> listAllMembers(){

        List<com.example.meetingsScheduler.entity.Member> memberEntitiesList = memberRepository.findAll();
        List<Member> memberModelList = new ArrayList<Member>();
        for (com.example.meetingsScheduler.entity.Member entity:memberEntitiesList){
            Member model = entityToModelMapper(entity);
            memberModelList.add(model);
        }
        return memberModelList;
    }

    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }

    public Member createMember(Member member){
        memberRepository.save(modelToEntityMapper(member));
        return member;
    }

    public Member updateMember(Member member){
        memberRepository.save(modelToEntityMapper(member));
        return member;
    }

    private Member entityToModelMapper(com.example.meetingsScheduler.entity.Member entity){
        Member model = new Member();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setEmail(entity.getEmail());
        Set<Meeting> meetingModelSet = new HashSet<Meeting>();
        Set<com.example.meetingsScheduler.entity.MeetingMember> meetingEntitiesSet = entity.getMeetingsList();
        for (MeetingMember meetingentity: meetingEntitiesSet){
            Meeting meetingModel = new Meeting();
            meetingModel.setId(meetingentity.getId());
            meetingModelSet.add(meetingModel);
        }
        model.setMeetingsList(meetingModelSet);

        return model;
    }

    private com.example.meetingsScheduler.entity.Member modelToEntityMapper(Member model){
        com.example.meetingsScheduler.entity.Member entity = new com.example.meetingsScheduler.entity.Member();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setEmail(model.getEmail());
        Set<Meeting> meetingModelSet = model.getMeetingsList();
        Set<MeetingMember> meetingEntitiesSet = new HashSet<MeetingMember>();
        for (Meeting meetingModel: meetingModelSet){
            MeetingMember meetingMemberEntity = new MeetingMember();
            meetingMemberEntity.setId(meetingModel.getId());
            meetingEntitiesSet.add(meetingMemberEntity);

        }
        entity.setMeetingsList(meetingEntitiesSet);
        return entity;
    }
}
