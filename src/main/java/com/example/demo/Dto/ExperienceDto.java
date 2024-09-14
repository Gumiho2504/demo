package com.example.demo.Dto;

import java.time.LocalDateTime;

public class ExperienceDto {
    private long id;
    private String title;
    private String description;
    private String company;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isWorking;

    public ExperienceDto() {
    }

    public ExperienceDto(long id, String title, String description, String company, LocalDateTime startDate,
            LocalDateTime endDate, Boolean isWorking) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.company = company;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isWorking = isWorking;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public Boolean isIsWorking() {
        return this.isWorking;
    }

    public Boolean getIsWorking() {
        return this.isWorking;
    }

    public void setIsWorking(Boolean isWorking) {
        this.isWorking = isWorking;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", title='" + getTitle() + "'" +
                ", description='" + getDescription() + "'" +
                ", company='" + getCompany() + "'" +
                ", startDate='" + getStartDate() + "'" +
                ", endDate='" + getEndDate() + "'" +
                ", isWorking='" + isIsWorking() + "'" +
                "}";
    }

}
