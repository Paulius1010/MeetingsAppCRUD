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
    private final String dataFetchingPath = "TestMeetingsFetching.json";
    private final String dataSavingPath = "TestMeetingSaving.json";

    @BeforeAll
    public static void initMapper() {
        mapper = new ObjectMapper();
    }

    MeetingsList fetchingAllMeetings(String filePath) throws IOException {
        return mapper.readValue(Paths.get(filePath).toFile(), MeetingsList.class);
    }


    @Test
    void fetchingAllMeetingsFromTestMeetingsFetchingFileShouldFetchAllMeetings() throws IOException {
        MeetingsList meetingsListObject = fetchingAllMeetings(dataFetchingPath);
        Integer expected = 5;
        Integer actual = meetingsListObject.getMeetings().size();
        assertEquals(expected, actual);
    }

    @Test
    void saveNewMeeting() throws IOException {
        List<Attendant> attendantList = new ArrayList<>();
        Person person = new Person("39112301598", "Petras Petraitis");
        attendantList.add(new Attendant(person));

        Meeting meeting = new Meeting(
                "java meeting",
                person,
                "test",
                HUB,
                LIVE,
                LocalDate.of(2022, 05, 28),
                LocalDate.of(2022, 05, 30),
                attendantList);
        List<Meeting> meetingsList = fetchingAllMeetings(dataSavingPath).getMeetings();

        Integer actual1 = meetingsList.size();
        meetingsList.add(meeting);
        mapper.writeValue(Paths.get(dataSavingPath).toFile(), new MeetingsList(meetingsList) );
        Integer actual2 = fetchingAllMeetings(dataSavingPath).getMeetings().size();

        assertEquals(1, actual2-actual1);
    }
}