package com.example.demo.Dto;

import java.time.LocalDateTime;

public class EducationDto {
    private long id;
    private String school;
    private String field;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;

    public EducationDto() {
    }

    public EducationDto(long id, String school, String field, LocalDateTime startDate, LocalDateTime endDate,
            String description) {
        this.id = id;
        this.school = school;
        this.field = field;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSchool() {
        return this.school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", school='" + getSchool() + "'" +
                ", field='" + getField() + "'" +
                ", startDate='" + getStartDate() + "'" +
                ", endDate='" + getEndDate() + "'" +
                ", description='" + getDescription() + "'" +
                "}";
    }

}
