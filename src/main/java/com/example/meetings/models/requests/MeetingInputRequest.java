package com.example.meetings.models.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NotNull
public class MeetingInputRequest {
    @JsonFormat
    @NotEmpty
    @NotBlank
    @NotNull
    String name;
    @NotEmpty
    @NotBlank
    @JsonFormat
    @NotNull
    String responsiblePersonId;
    @NotEmpty
    @NotBlank
    @JsonFormat
    @NotNull
    String responsiblePersonFullName;
    @NotEmpty
    @NotBlank
    @JsonFormat
    @NotNull
    String description;
    @NotEmpty
    @NotBlank
    @JsonFormat
    @NotNull
    String category;
    @NotEmpty
    @NotBlank
    @JsonFormat
    @NotNull
    String type;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate endDate;

    public MeetingInputRequest(String name,
                               String responsiblePersonId,
                               String responsiblePersonFullName,
                               String description,
                               String category,
                               String type,
                               LocalDate startDate,
                               LocalDate endDate) {
        this.name = name;
        this.responsiblePersonId = responsiblePersonId;
        this.responsiblePersonFullName = responsiblePersonFullName;
        this.description = description;
        this.category = category;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResponsiblePersonId() {
        return responsiblePersonId;
    }

    public void setResponsiblePersonId(String responsiblePersonId) {
        this.responsiblePersonId = responsiblePersonId;
    }

    public String getResponsiblePersonFullName() {
        return responsiblePersonFullName;
    }

    public void setResponsiblePersonFullName(String responsiblePersonFullName) {
        this.responsiblePersonFullName = responsiblePersonFullName;
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
}

