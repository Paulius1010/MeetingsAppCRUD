package com.example.meetings.services;

import com.example.meetings.domains.*;
import com.example.meetings.models.requests.*;
import com.example.meetings.models.responses.MeetingInputResponse;
import com.example.meetings.models.responses.MeetingResponse;
import com.example.meetings.repositories.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MeetingAttendantsService {

    private final MeetingRepository meetingRepository;

    @Autowired
    public MeetingAttendantsService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public String saveNewAttendant(String id, AttendantInputRequest attendantInputRequest) {
        List<Meeting> meetingsList = meetingRepository.getAllActiveMeetings();
        Optional<Meeting> updatingMeeting = meetingsList.stream().filter(meeting -> meeting.getId().equals(id)).findFirst();
        if (updatingMeeting.isEmpty()) {
            return "Meeting " + id + "(" + ")" + " not found";
        } else {
            List<Attendant> attendantList = updatingMeeting.get().getAttendants();
            Optional<Attendant> searchingAttendant = attendantList
                    .stream()
                    .filter(attendant -> attendant.getPerson()
                            .getPersonId()
                            .equals(attendantInputRequest.getAttendantId()))
                    .findFirst();
            if (searchingAttendant.isPresent()) {
                return attendantInputRequest.getAttendantFullName() + " is already in the meeting " + "\"" + updatingMeeting.get().getName() + "\"" +" (" + id +")";
            }
            Attendant newAttendant = new Attendant(new Person(attendantInputRequest.getAttendantId(), attendantInputRequest.getAttendantFullName()));
            attendantList.add(newAttendant);
                meetingRepository.saveNewMeetingsList(meetingsList);
            return "New attendant " + newAttendant.getPerson().getPersonFullName() + " added to the meeting " + "\"" + updatingMeeting.get().getName() + "\"" +" ("  + id +")";
        }
    }

    public String removeAttendant(String id, AttendantRemoveRequest attendantRemoveRequest) {
        List<Meeting> meetingsList = meetingRepository.getAllActiveMeetings();
        Optional<Meeting> updatingMeeting = meetingsList.stream().filter(meeting -> meeting.getId().equals(id)).findFirst();
        if (updatingMeeting.isEmpty()) {
            return "Meeting " + id + " not found";
        } else {
            List<Attendant> meetingAttendants = updatingMeeting.get().getAttendants();
            Optional<Attendant> searchingAttendant = meetingAttendants
                    .stream()
                    .filter(attendant -> attendant.getPerson()
                            .getPersonId()
                            .equals(attendantRemoveRequest.getAttendantId()))
                    .findFirst();
            if (searchingAttendant.isEmpty()) {
                return attendantRemoveRequest.getAttendantFullName() + " does not attend in the meeting " + "\"" + updatingMeeting.get().getName() + "\"" +" ("  + id +")";
            }
            meetingAttendants.remove(searchingAttendant.get());
            meetingRepository.saveNewMeetingsList(meetingsList);
            return "Attendant " + searchingAttendant.get().getPerson().getPersonFullName() + " deleted from the meeting " + "\"" + updatingMeeting.get().getName() + "\"" +" ("  + id +")";
        }
    }
}
