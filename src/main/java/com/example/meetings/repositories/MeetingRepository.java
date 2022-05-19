package com.example.meetings.repositories;

import com.example.meetings.domains.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Repository;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MeetingRepository {

    private final String dataPath = "meetings.json";

    public List<Meeting> getAllActiveMeetings() {
        return getAllMeetingsFromFile().stream().filter(meeting -> !meeting.getEndDate().isBefore(LocalDate.now())).collect(Collectors.toList());
    }

    public void saveMeeting(Meeting meeting) {
                List<Meeting> meetingsList = getAllMeetingsFromFile();
        meetingsList.add(meeting);
        saveNewMeetingsList(meetingsList);
    }

    public void saveNewMeetingsList(List<Meeting> meetingsList) {
        try {
             ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(Paths.get(dataPath).toFile(), new MeetingsList(meetingsList));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Meeting> getAllMeetingsFromFile() {
        MeetingsList meetingsListObject = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            meetingsListObject = mapper.readValue(Paths.get(dataPath).toFile(), MeetingsList.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (meetingsListObject == null) {
            return new ArrayList<>();
        }
        return meetingsListObject.getMeetings();
    }
}
