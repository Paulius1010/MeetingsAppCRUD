package com.example.meetings.models.requests;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NotNull
public class AttendantRemoveRequest {
    @JsonFormat
    @NotNull
    @NotEmpty
    @NotBlank
    private String attendantId;
    @JsonFormat
    @NotNull
    @NotEmpty
    @NotBlank
    private String attendantFullName;

    public AttendantRemoveRequest(String attendantId, String attendantFullName) {
        this.attendantId = attendantId;
        this.attendantFullName = attendantFullName;
    }

    public AttendantRemoveRequest() {
    }

    public String getAttendantId() {
        return attendantId;
    }

    public void setAttendantId(String attendantId) {
        this.attendantId = attendantId;
    }

    public String getAttendantFullName() {
        return attendantFullName;
    }

    public void setAttendantFullName(String attendantFullName) {
        this.attendantFullName = attendantFullName;
    }

}
