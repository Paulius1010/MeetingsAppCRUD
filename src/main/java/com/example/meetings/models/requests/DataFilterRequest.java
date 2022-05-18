package com.example.meetings.models.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NotNull
public class DataFilterRequest {

    @JsonFormat
    private String responsiblePersonId;
    @JsonFormat
    private String description;
    @JsonFormat
    private String category;
    @JsonFormat
    private String type;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate periodRangeLowerBound;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate periodRangeUpperBound;
    @JsonFormat
    @Min(0)
    private Integer attendeesMoreThan;

    public String getResponsiblePersonId() {
        return responsiblePersonId;
    }

    public void setResponsiblePersonId(String responsiblePersonId) {
        this.responsiblePersonId = responsiblePersonId;
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

    public LocalDate getPeriodRangeLowerBound() {
        return periodRangeLowerBound;
    }

    public void setPeriodRangeLowerBound(LocalDate periodRangeLowerBound) {
        this.periodRangeLowerBound = periodRangeLowerBound;
    }

    public LocalDate getPeriodRangeUpperBound() {
        return periodRangeUpperBound;
    }

    public void setPeriodRangeUpperBound(LocalDate periodRangeUpperBound) {
        this.periodRangeUpperBound = periodRangeUpperBound;
    }

    public Integer getAttendeesMoreThan() {
        return attendeesMoreThan;
    }

    public void setAttendeesMoreThan(Integer attendeesMoreThan) {
        this.attendeesMoreThan = attendeesMoreThan;
    }
}
