//package com.example.meetings.models.responses;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
//
//import java.time.LocalDate;
//
//public class AttendantInputResponse {
//    @JsonFormat
//    private String attendantId;
//    @JsonFormat
//    private String attendantFullName;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonSerialize(using = LocalDateSerializer.class)
//    private LocalDate registrationDate;
//
//    public AttendantInputResponse(String attendantId, String attendantFullName, LocalDate registrationDate) {
//        this.attendantId = attendantId;
//        this.attendantFullName = attendantFullName;
//        this.registrationDate = registrationDate;
//    }
//
//    public AttendantInputResponse() {
//    }
//
//    public String getAttendantId() {
//        return attendantId;
//    }
//
//    public void setAttendantId(String attendantId) {
//        this.attendantId = attendantId;
//    }
//
//    public String getAttendantFullName() {
//        return attendantFullName;
//    }
//
//    public void setAttendantFullName(String attendantFullName) {
//        this.attendantFullName = attendantFullName;
//    }
//
//    public LocalDate getRegistrationDate() {
//        return registrationDate;
//    }
//
//    public void setRegistrationDate(LocalDate registrationDate) {
//        this.registrationDate = registrationDate;
//    }
//}
