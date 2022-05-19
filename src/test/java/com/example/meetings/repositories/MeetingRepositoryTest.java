package com.example.meetings.repositories;

import com.example.meetings.domains.Attendant;
import com.example.meetings.domains.Meeting;
import com.example.meetings.domains.MeetingsList;
import com.example.meetings.domains.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.meetings.domains.Category.HUB;
import static com.example.meetings.domains.Type.LIVE;
import static org.junit.jupiter.api.Assertions.*;

class MeetingRepositoryTest {

    private static ObjectMapper mapper;

    @BeforeAll
    public static void initMapper() {
        mapper = new ObjectMapper();
    }

    MeetingsList fetchingAllMeetings(String filePath) throws IOException {
        return mapper.readValue(Paths.get(filePath).toFile(), MeetingsList.class);
    }


    @Test
    void fetchingAllMeetingsFromTestMeetingsFileShouldFetchAllMeetings() throws IOException {
        MeetingsList meetingsListObject = fetchingAllMeetings("TestMeetingsFetching.json");
        Integer expected = 2;
        Integer actual = meetingsListObject.getMeetings().size();
        assertEquals(expected, actual);

    }

    @Test
    void saveNewMeeting() throws IOException {
        List<Attendant> attendantList = new ArrayList<>();
        attendantList.add(new Attendant(new Person("39112301598", "Petras Petraitis")));

        Meeting meeting = new Meeting("java meeting", new Person("39112301598", "Petras Petraitis"), "test", HUB, LIVE, LocalDate.of(2022, 05, 28), LocalDate.of(2022, 05, 30), attendantList);
        List<Meeting> meetingsList = fetchingAllMeetings("TestMeetingSaving.json").getMeetings();

        Integer actual1 = meetingsList.size();
        meetingsList.add(meeting);
        mapper.writeValue(Paths.get("TestMeetingSaving.json").toFile(), new MeetingsList(meetingsList) );
        Integer actual2 = fetchingAllMeetings("TestMeetingSaving.json").getMeetings().size();

        assertEquals(1, actual2-actual1);



    }
}