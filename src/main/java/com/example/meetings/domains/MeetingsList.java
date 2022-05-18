package com.example.meetings.domains;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

public class MeetingsList {

    @JsonFormat
    private List<Meeting> meetings;

    public MeetingsList(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public MeetingsList() {
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }
}
