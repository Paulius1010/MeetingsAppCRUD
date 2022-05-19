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
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private List<Meeting> meetingsList;

    @Autowired
    public MeetingService(MeetingRepository meetingRepository, List<Meeting> meetingsList) {
        this.meetingRepository = meetingRepository;
        this.meetingsList = meetingsList;
    }

    public List<MeetingResponse> fetchAllActiveFilteredMeetings(DataFilterRequest dataFilterRequest) {
        this.meetingsList = meetingRepository.getAllActiveMeetings();

        String description = dataFilterRequest.getDescription();
                if (description != null && !description.isEmpty()) {
            this.meetingsList = filterByDescription(meetingsList, description);
        }

        String responsiblePersonId = dataFilterRequest.getResponsiblePersonId();
        if (responsiblePersonId != null && !responsiblePersonId.isEmpty()) {
            this.meetingsList = filterByResponsiblePersonId(meetingsList, responsiblePersonId);
        }

        String categoryString = dataFilterRequest.getCategory();
        if (categoryString != null && !categoryString.isEmpty()) {
            Category category = Category.getCategoryFromString(dataFilterRequest.getCategory());
            this.meetingsList = filterByCategory(meetingsList, category);
        }

        String typeString = dataFilterRequest.getType();
        if (typeString != null && !typeString.isEmpty()) {
            Type type = Type.getTypeFromString(dataFilterRequest.getType());
            this.meetingsList = filterByType(meetingsList, type);
        }

        Integer attendeesMoreThan = dataFilterRequest.getAttendeesMoreThan();
        if (attendeesMoreThan != null) {
            this.meetingsList = filterByAttendeesNumber(meetingsList, attendeesMoreThan);
        }

        LocalDate periodRangeLowerBound = dataFilterRequest.getPeriodRangeLowerBound();
        if (periodRangeLowerBound != null) {
            this.meetingsList = filterByPeriodRangeLowerBound(meetingsList, periodRangeLowerBound);
        }

        LocalDate periodRangeUpperBound = dataFilterRequest.getPeriodRangeUpperBound();
        if (periodRangeUpperBound != null) {
            this.meetingsList = filterByPeriodRangeUpperBound(meetingsList, periodRangeUpperBound);
        }

        List<MeetingResponse> meetingsResponsesList = new ArrayList<>();

        for (Meeting meeting : meetingsList) {

            MeetingResponse meetingResponse = new MeetingResponse(
                    meeting.getId(),
                    meeting.getName(),
                    meeting.getResponsiblePerson(),
                    meeting.getDescription(),
                    meeting.getCategory().getValue(),
                    meeting.getType().getValue(),
                    meeting.getStartDate(),
                    meeting.getEndDate(),
                    meeting.getAttendants()
            );
            meetingsResponsesList.add(meetingResponse);
        }

        return meetingsResponsesList;
    }

    private List<Meeting> filterByPeriodRangeLowerBound(List<Meeting> meetingsList, LocalDate periodRangeLowerBound) {
        return meetingsList.stream().filter(meeting -> !meeting.getEndDate().isBefore(periodRangeLowerBound)).collect(Collectors.toList());
    }

    private List<Meeting> filterByPeriodRangeUpperBound(List<Meeting> meetingsList, LocalDate periodRangeUpperBound) {
        return meetingsList.stream().filter(meeting -> !meeting.getStartDate().isAfter(periodRangeUpperBound)).collect(Collectors.toList());
    }

    private List<Meeting> filterByAttendeesNumber(List<Meeting> meetingsList, Integer attendeesMoreThan) {
        return meetingsList.stream().filter(meeting -> meeting.getAttendants().size() > attendeesMoreThan).collect(Collectors.toList());
    }

    private List<Meeting> filterByType(List<Meeting> meetingsList, Type type) {
        return meetingsList.stream().filter(meeting -> meeting.getType() == type).collect(Collectors.toList());
    }

    private List<Meeting> filterByCategory(List<Meeting> meetingsList, Category category) {
        return meetingsList.stream().filter(meeting -> meeting.getCategory() == category).collect(Collectors.toList());
    }

    private List<Meeting> filterByResponsiblePersonId(List<Meeting> meetingsList, String responsiblePersonId) {
        return meetingsList.stream().filter(meeting -> meeting.getResponsiblePerson().getPersonId().equals(responsiblePersonId)).collect(Collectors.toList());
    }

    private List<Meeting> filterByDescription(List<Meeting> meetingsList, String description) {
        return meetingsList.stream().filter(meeting -> meeting.getDescription().contains(description)).collect(Collectors.toList());

    }

    public MeetingInputResponse saveNewMeeting(MeetingInputRequest meetingInputRequest) {
        Person person = new Person(
                meetingInputRequest.getResponsiblePersonId(),
                meetingInputRequest.getResponsiblePersonFullName());

        Attendant attendant = new Attendant(person);
        List<Attendant> attendants = new ArrayList<>();
        attendants.add(attendant);

        Category category = Category.getCategoryFromString(meetingInputRequest.getCategory());
        Type type = Type.getTypeFromString(meetingInputRequest.getType());

        Meeting meeting = new Meeting(
                meetingInputRequest.getName(),
                person,
                meetingInputRequest.getDescription(),
                category,
                type,
                meetingInputRequest.getStartDate(),
                meetingInputRequest.getEndDate(),
                attendants
        );
        meetingRepository.saveMeeting(meeting);

        return new MeetingInputResponse(
                meeting.getId(),
                meetingInputRequest.getName(),
                meetingInputRequest.getResponsiblePersonId(),
                meetingInputRequest.getResponsiblePersonFullName(),
                meetingInputRequest.getDescription(),
                meetingInputRequest.getCategory(),
                meetingInputRequest.getType(),
                meetingInputRequest.getStartDate(),
                meetingInputRequest.getEndDate(),
                meeting.getAttendants());
    }

    public String deleteMeeting(MeetingDeleteRequest meetingDeleteRequest) {
        String id = meetingDeleteRequest.getMeetingId();
        List<Meeting> meetingsList = meetingRepository.getAllActiveMeetings();
        Optional<Meeting> removingMeeting = meetingsList.stream().filter(meeting -> meeting.getId().equals(id)).findFirst();
        if (removingMeeting.isEmpty()) {
            return "Meeting with such an id does not exist";
        } else {
            Meeting meeting = removingMeeting.get();
            if (meeting.getResponsiblePerson().getPersonId().equals(meetingDeleteRequest.getPersonId())) {
                meetingsList.remove(meeting);
                meetingRepository.saveNewMeetingsList(meetingsList);
                return "Meeting with id " + id + " deleted";
            }
            return "Meeting with id " + id + " depends to other user";
        }
    }
}
