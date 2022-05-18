package com.example.meetings.models.requests;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NotNull
public class MeetingDeleteRequest {
    @JsonFormat
    @NotEmpty
    @NotBlank
    String meetingId;
    @JsonFormat
    @NotEmpty
    @NotBlank
    String personId;

    public MeetingDeleteRequest(String meetingId, String personId) {
        this.meetingId = meetingId;
        this.personId = personId;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
