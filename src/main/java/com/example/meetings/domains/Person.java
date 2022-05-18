package com.example.meetings.domains;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Person {
    @JsonFormat
    private String personId;
    @JsonFormat
    private String personFullName;

    public Person(String personId, String personFullName) {
        this.personId = personId;
        this.personFullName = personFullName;
    }

    public Person() {
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonFullName() {
        return personFullName;
    }

    public void setPersonFullName(String personFullName) {
        this.personFullName = personFullName;
    }

    @Override
    public String toString() {
        return "ResponsiblePerson{" +
                "personId='" + personId + '\'' +
                ", personFullName='" + personFullName + '\'' +
                '}';
    }
}
