package com.example.meetings.services;

import com.example.meetings.models.requests.AttendantInputRequest;
import com.example.meetings.models.requests.AttendantRemoveRequest;
import com.example.meetings.repositories.MeetingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MeetingAttendantsServiceTest {


    private static ObjectMapper mapper;

    @BeforeAll
    public static void initMapper() {
        mapper = new ObjectMapper();
    }

    @Test
    void testSavingNewAttendantWhenSelectedMeetingDoesNotExist() {
        MeetingAttendantsService meetingAttendantsService = new MeetingAttendantsService(new MeetingRepository());
        AttendantInputRequest attendantInputRequest = new AttendantInputRequest(
                "45678963321",
                "Irma Irmaitė");
        String expected = "Meeting " + "x" + " not found";
        String actual = meetingAttendantsService.saveNewAttendant("x", attendantInputRequest);
        assertEquals(expected, actual);
    }

    @Test
    void testDeletingAttendantWhenSelectedMeetingDoesNotExist() {
        MeetingAttendantsService meetingAttendantsService = new MeetingAttendantsService(new MeetingRepository());
        AttendantRemoveRequest attendantRemoveRequest = new AttendantRemoveRequest(
                "45678963321",
                "Irma Irmaitė");
        String expected = "Meeting " + "x" + " not found";
        String actual = meetingAttendantsService.removeAttendant("x", attendantRemoveRequest);
        assertEquals(expected, actual);
    }

}