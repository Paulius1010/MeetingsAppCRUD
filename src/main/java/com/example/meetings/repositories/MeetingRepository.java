package com.example.meetings.repositories;
import com.example.meetings.domains.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Repository;

import java.nio.file.Paths;
import java.util.List;

@Repository
public class MeetingRepository {

    public List<Meeting> getAllActiveMeetings() {
        return getAllActiveMeetingsFromFile();
    }

    public void saveMeeting(Meeting meeting) {
                List<Meeting> meetingsList = getAllActiveMeetingsFromFile();
        meetingsList.add(meeting);
        saveNewMeetingsList(meetingsList);
    }

    public void saveNewMeetingsList(List<Meeting> meetingsList) {
        try {
             ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(Paths.get("meetings.json").toFile(), new MeetingsList(meetingsList) );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private List<Meeting> getAllActiveMeetingsFromFile() {
        MeetingsList meetingsListObject = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            meetingsListObject = mapper.readValue(Paths.get("meetings.json").toFile(), MeetingsList.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        assert meetingsListObject != null;
        return meetingsListObject.getMeetings();
    }
}
