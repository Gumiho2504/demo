package com.example.demo.Dto;

import java.util.List;

import com.example.demo.model.Requestment;

public class JobDto {

    private long id;
    private String title;
    private String description;
    private String salary;
    private String workModel;
    private String type;
    private String level;
    private CompanyDto company;
    private List<RequestmentDto> requestments;

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

    public String getSalary() {
        return this.salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getWorkModel() {
        return this.workModel;
    }

    public void setWorkModel(String workModel) {
        this.workModel = workModel;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public CompanyDto getCompany() {
        return this.company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    public List<RequestmentDto> getRequestments() {
        return this.requestments;
    }

    public void setRequestments(List<RequestmentDto> requestments) {
        this.requestments = requestments;
    }

}
