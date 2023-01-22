package com.example.meetingsScheduler.service;

import com.example.meetingsScheduler.entity.MeetingAgenda;
import com.example.meetingsScheduler.entity.MeetingMember;
import com.example.meetingsScheduler.entity.Member;
import com.example.meetingsScheduler.entity.Role;
import com.example.meetingsScheduler.model.Agenda;
import com.example.meetingsScheduler.model.Meeting;
import com.example.meetingsScheduler.repository.MeetingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MeetingService {

    @Autowired
    MeetingRepository meetingRepository;

    public List<Meeting> getAll(){
        List<com.example.meetingsScheduler.entity.Meeting> meetingEntities = meetingRepository.findAll();
        List<Meeting> meetingList = meetingEntityToModelMapper(meetingEntities);
        return meetingList;
    }

    public void deleteMeeting(Long id){

        meetingRepository.deleteById(id);
    }

    @Transactional
    public Meeting createMeeting(Meeting model) {
        if(model.getAgendaSet()==null || model.getAgendaSet().size()<1||
                model.getMemberSet()==null || model.getMemberSet().size()<2 ||
                !model.getMemberSet().stream().anyMatch(m->m.getRole().equals(Role.B))
        ){
            return null;  //Roles to create new meeting failed ..
        }
        meetingRepository.save(modelToEntityMapper(model));
        return model;
    }



    public Meeting updateMeeting(Meeting model){
        if(model.getAgendaSet()==null || model.getAgendaSet().size()<1||
                model.getMemberSet()==null || model.getMemberSet().size()<2 ||
                !model.getMemberSet().stream().anyMatch(m->m.getRole().equals(Role.B))
        ){
            return null;
        }
        Optional<com.example.meetingsScheduler.entity.Meeting> entity = meetingRepository.findById(model.getId());
        if(entity.isPresent()){

            meetingRepository.save(modelToEntityMapper(model));
            return model;
        }
        return null;
    }

    private List<Meeting> meetingEntityToModelMapper(List<com.example.meetingsScheduler.entity.Meeting> meetingEntities){
        List<Meeting> meetingList = new ArrayList<Meeting>();
        for (com.example.meetingsScheduler.entity.Meeting entity:meetingEntities) {
            Meeting meetingModel = new Meeting();
            meetingModel.setMeetingDate(entity.getMeetingDate());
            meetingModel.setStatus(entity.getStatus());
            meetingModel.setStartTime(entity.getStartTime());
            meetingModel.setEndTime(entity.getEndTime());
            meetingModel.setSubject(entity.getSubject());



            meetingList.add(meetingModel);
        }
        return meetingList;
    }

    private com.example.meetingsScheduler.entity.Meeting modelToEntityMapper(Meeting model){
        com.example.meetingsScheduler.entity.Meeting meetingEntity = new com.example.meetingsScheduler.entity.Meeting();
        meetingEntity.setId(model.getId());
        meetingEntity.setStatus(model.getStatus());
        meetingEntity.setSubject(model.getSubject());
        meetingEntity.setMeetingDate(model.getMeetingDate());
        meetingEntity.setStartTime(model.getStartTime());
        meetingEntity.setEndTime(model.getEndTime());

        Set<com.example.meetingsScheduler.model.Member> modelMemberSet = model.getMemberSet();
        for (com.example.meetingsScheduler.model.Member modelMember: modelMemberSet) {
            Member entityMember = new Member();
            entityMember.setId(modelMember.getId());
            entityMember.setName(modelMember.getName());
            entityMember.setEmail(modelMember.getEmail());
            entityMember.setMeetingsList(null);
        }
        Set<Agenda> modelAgendaSet = model.getAgendaSet();
        for(Agenda agenda:modelAgendaSet){
            MeetingAgenda meetingAgenda = new MeetingAgenda();
            meetingAgenda.setId(agenda.getId());
            meetingAgenda.setTitle(agenda.getTitle());
            meetingEntity.getMeetingAgendaSet().add(meetingAgenda);
        }

        return meetingEntity;
    }

    private Meeting entityToModelMapper(com.example.meetingsScheduler.entity.Meeting entity){
        Meeting meetingModel = new Meeting();
        meetingModel.setId(entity.getId());
        meetingModel.setSubject(entity.getSubject());
        meetingModel.setStatus(entity.getStatus());
        meetingModel.setMeetingDate(entity.getMeetingDate());
        meetingModel.setEndTime(entity.getEndTime());
        meetingModel.setStartTime(entity.getStartTime());

        Set<MeetingMember> entityMeetingMemberSet = entity.getMeetingMemberSet();
        for (MeetingMember mMember:entityMeetingMemberSet) {
            com.example.meetingsScheduler.model.Member modelMember = new com.example.meetingsScheduler.model.Member();
            modelMember.setId(mMember.getId());
            modelMember.setName(modelMember.getName());
            modelMember.setEmail(modelMember.getEmail());
            modelMember.setRole(modelMember.getRole());
            modelMember.setMeetingsList(null);
            meetingModel.getMemberSet().add(modelMember);
        }
        Set<MeetingAgenda> meetingAgendaSet = entity.getMeetingAgendaSet();
        for(MeetingAgenda meetingAgenda : meetingAgendaSet){
            Agenda agendaModel = new Agenda();
            agendaModel.setId(meetingAgenda.getId());
            agendaModel.setTitle(meetingAgenda.getTitle());
            meetingModel.getAgendaSet().add(agendaModel);
        }
        return meetingModel;
    }

}
