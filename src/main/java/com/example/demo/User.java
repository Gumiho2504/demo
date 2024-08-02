package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Job> saveJobs;

    public void addJob(Job job){
        if(saveJobs == null){
            saveJobs = new ArrayList<>();
        }
        saveJobs.add(job);
        job.setUser(this);
    }

    public void removeJob(Job job) {
        this.saveJobs.remove(job);
        job.setUser(null);
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", saveJobs='" + getSaveJobs() + "'" +
            "}";
    }


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

    public List<Job> getSaveJobs() {
        return this.saveJobs;
    }

    public void setSaveJobs(List<Job> saveJobs) {
        this.saveJobs = saveJobs;
    }


}
