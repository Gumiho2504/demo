package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

import java.util.Objects;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profile;
    private LocalDateTime createdAt;

    public User() {
        this.createdAt = LocalDateTime.now();
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinTable(name = "savejob", // Join table name
            joinColumns = @JoinColumn(name = "user_id"), // Column for Student
            inverseJoinColumns = @JoinColumn(name = "job_id") // Column for Course
    )
    @JsonManagedReference
    private List<Job> saveJobs;

    public void addJob(Job job) {
        if (saveJobs == null) {
            saveJobs = new ArrayList<>();
        }
        saveJobs.add(job);
        // job.add(this);
    }

    public void removeJob(Job job) {
        this.saveJobs.remove(job);
        // job.setUser(null);
    }

    public User(long id, String name, String email, String password, LocalDateTime createdAt, List<Job> saveJobs) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.saveJobs = saveJobs;
    }

    public User(long id, String name, String email, String password, Profile profile, LocalDateTime createdAt,
            List<Job> saveJobs) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.profile = profile;
        this.createdAt = createdAt;
        this.saveJobs = saveJobs;
    }

    public Profile getProfile() {
        return this.profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public User profile(Profile profile) {
        setProfile(profile);
        return this;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User id(long id) {
        setId(id);
        return this;
    }

    public User name(String name) {
        setName(name);
        return this;
    }

    public User email(String email) {
        setEmail(email);
        return this;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public User createdAt(LocalDateTime createdAt) {
        setCreatedAt(createdAt);
        return this;
    }

    public User saveJobs(List<Job> saveJobs) {
        setSaveJobs(saveJobs);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(email, user.email)
                && Objects.equals(password, user.password) && Objects.equals(createdAt, user.createdAt)
                && Objects.equals(saveJobs, user.saveJobs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, createdAt, saveJobs);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", email='" + getEmail() + "'" +
                ", password='" + getPassword() + "'" +
                ", createdAt='" + getCreatedAt() + "'" +
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
