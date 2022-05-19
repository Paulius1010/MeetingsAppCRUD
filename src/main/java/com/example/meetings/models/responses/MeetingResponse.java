package com.example.meetings.models.responses;

import com.example.meetings.domains.Attendant;
import com.example.meetings.domains.Person;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.List;

public class MeetingResponse {
        @JsonFormat
        String id;
        @JsonFormat
        String name;
        @JsonFormat
        Person responsiblePerson;
        @JsonFormat
        String description;
        @JsonFormat
        String category;
        @JsonFormat
        String type;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        LocalDate startDate;
        @JsonFormat
        LocalDate endDate;
        @JsonFormat
        List<Attendant> attendants;

        public MeetingResponse(String id,
                               String name,
                               Person responsiblePerson,
                               String description,
                               String category,
                               String type,
                               LocalDate startDate,
                               LocalDate endDate,
                               List<Attendant> attendants) {
                this.id = id;
                this.name = name;
                this.responsiblePerson = responsiblePerson;
                this.description = description;
                this.category = category;
                this.type = type;
                this.startDate = startDate;
                this.endDate = endDate;
                this.attendants = attendants;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Person getResponsiblePerson() {
                return responsiblePerson;
        }

        public void setResponsiblePerson(Person responsiblePerson) {
                this.responsiblePerson = responsiblePerson;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getCategory() {
                return category;
        }

        public void setCategory(String category) {
                this.category = category;
        }

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }

        public LocalDate getStartDate() {
                return startDate;
        }

        public void setStartDate(LocalDate startDate) {
                this.startDate = startDate;
        }

        public LocalDate getEndDate() {
                return endDate;
        }

        public void setEndDate(LocalDate endDate) {
                this.endDate = endDate;
        }

        public List<Attendant> getAttendants() {
                return attendants;
        }

        public void setAttendants(List<Attendant> attendants) {
                this.attendants = attendants;
        }
}