package com.example.meetings.services;

import com.example.meetings.domains.Category;
import com.example.meetings.domains.Meeting;
import com.example.meetings.domains.MeetingsList;
import com.example.meetings.domains.Type;
import com.example.meetings.repositories.MeetingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MeetingAttendantsServiceTest {


    private static ObjectMapper mapper;
    private static final String dataFetchingPath = "TestMeetingsFetching.json";
    private static MeetingsList meetingsListObject;

    @BeforeAll
    public static void initMapper() throws IOException {
        mapper = new ObjectMapper();
        meetingsListObject = mapper.readValue(Paths.get(dataFetchingPath).toFile(), MeetingsList.class);
    }

    @Test
    void testFilterByPeriodRangeLowerBound20990529() {
        MeetingService meetingService = new MeetingService(new MeetingRepository());
        List<Meeting> meetingList = meetingService.filterByPeriodRangeLowerBound(
                meetingsListObject.getMeetings(),
                LocalDate.of(2099, 05, 29));
        Integer expected = 4;
        Integer actual = meetingList.size();
        assertEquals(expected, actual);
    }

    @Test
    void testFilterByPeriodRangeUpperBound20990518() {
        MeetingService meetingService = new MeetingService(new MeetingRepository());
        List<Meeting> meetingList = meetingService.filterByPeriodRangeUpperBound(
                meetingsListObject.getMeetings(),
                LocalDate.of(2099, 05, 18));
        Integer expected = 1;
        Integer actual = meetingList.size();
        assertEquals(expected, actual);
    }

    @Test
    void testFilterByAttendeesNumberMoreThan1() {
        MeetingService meetingService = new MeetingService(new MeetingRepository());
        List<Meeting> meetingList = meetingService.filterByAttendeesNumber(
                meetingsListObject.getMeetings(),
                1);
        Integer expected = 2;
        Integer actual = meetingList.size();
        assertEquals(expected, actual);
    }

    @Test
    void testFilterByTypeLive() {
        MeetingService meetingService = new MeetingService(new MeetingRepository());
        List<Meeting> meetingList = meetingService.filterByType(
                meetingsListObject.getMeetings(),
                Type.LIVE);
        Integer expected = 4;
        Integer actual = meetingList.size();
        assertEquals(expected, actual);
    }

    @Test
    void testFilterByCategoryHub() {
        MeetingService meetingService = new MeetingService(new MeetingRepository());
        List<Meeting> meetingList = meetingService.filterByCategory(
                meetingsListObject.getMeetings(),
                Category.HUB);
        Integer expected = 3;
        Integer actual = meetingList.size();
        assertEquals(expected, actual);
    }


    @Test
    void testFilterByResponsiblePersonId39112301598() {
        MeetingService meetingService = new MeetingService(new MeetingRepository());
        List<Meeting> meetingList = meetingService.filterByResponsiblePersonId(
                meetingsListObject.getMeetings(),
                "39112301598");
        Integer expected = 2;
        Integer actual = meetingList.size();
        assertEquals(expected, actual);
    }


    @Test
    void testFilterByDescriptionJava() {
        MeetingService meetingService = new MeetingService(new MeetingRepository());
        List<Meeting> meetingList = meetingService.filterByDescription(
                meetingsListObject.getMeetings(),
                "java");
        Integer expected = 2;
        Integer actual = meetingList.size();
        assertEquals(expected, actual);
    }
}