package com.example.meetings.controllers;
import com.example.meetings.models.requests.*;
import com.example.meetings.models.responses.MeetingInputResponse;
import com.example.meetings.models.responses.MeetingResponse;
import com.example.meetings.services.MeetingAttendantsService;
import com.example.meetings.services.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/api")
public class MeetingController {

    private final MeetingService meetingService;
    private final MeetingAttendantsService meetingAttendantsService;

    @Autowired
    public MeetingController(MeetingService meetingService, MeetingAttendantsService meetingAttendantsService) {
        this.meetingService = meetingService;
        this.meetingAttendantsService = meetingAttendantsService;
    }

    @GetMapping(value = "/meetings")
    @ResponseStatus(HttpStatus.OK)
    List<MeetingResponse> fetchAllActiveMeetings(@Valid @RequestBody DataFilterRequest dataFilterRequest) {
        return meetingService.fetchAllActiveMeetings(dataFilterRequest);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    MeetingInputResponse insertNewMeeting(@Valid @RequestBody MeetingInputRequest meetingInputRequest) {
        return meetingService.saveNewMeeting(meetingInputRequest);
    }

    @DeleteMapping(value = "/meetings")
    @ResponseStatus(HttpStatus.OK)
    String deleteMeeting(@Valid @RequestBody MeetingDeleteRequest meetingDeleteRequest) {
        return meetingService.deleteMeeting(meetingDeleteRequest);
    }

    @PutMapping(value = "/meetings/update/add/{id}")
    @ResponseStatus(HttpStatus.OK)
    String insertNewAttendant(@Valid @PathVariable String id, @RequestBody AttendantInputRequest attendantInputRequest) {
        return meetingAttendantsService.saveNewAttendant(id, attendantInputRequest);
    }

    @PutMapping(value = "/meetings/update/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    String removeAttendant(@Valid @PathVariable String id, @RequestBody AttendantRemoveRequest attendantRemoveRequest) {
        return meetingAttendantsService.removeAttendant(id, attendantRemoveRequest);
    }
}
