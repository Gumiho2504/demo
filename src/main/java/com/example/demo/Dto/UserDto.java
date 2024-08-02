package com.example.demo.Dto;

import java.util.List;



public class UserDto {
    private long id;
    private String name;
    private List<JobDto> saveJob;


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<JobDto> getSaveJob() {
        return this.saveJob;
    }

    public void setSaveJob(List<JobDto> saveJob) {
        this.saveJob = saveJob;
    }
    

    
}
