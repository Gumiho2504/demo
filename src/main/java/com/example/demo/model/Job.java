package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "user" })
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;
    private String salary;
    private String workModel;
    private String type;
    private String level;

    @OneToMany(mappedBy = "job")
    private List<Requestment> requestments;

    private void addRequestment(Requestment requestment) {
        if (this.requestments == null)
            requestments = new ArrayList<>();
        else
            this.requestments.add(requestment);
    }

    private void removeRequestment(Requestment requestment) {
        requestments.remove(requestment);
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinTable(name = "savejob", // Join table name
            joinColumns = @JoinColumn(name = "job_id"), // Column for Student
            inverseJoinColumns = @JoinColumn(name = "user_id") // Column for Course
    )
    @JsonBackReference

    List<User> user;

    public void addUser(User userT) {
        if (this.user == null) {
            user = new ArrayList<>();
        }
        user.add(userT);
    }

    public void removeUser(User userT) {
        this.user.remove(userT);
        // user.remove
    }

    // constructor

    public Job() {
    }

    public Job(long id, String title, String description, String salary, String workModel, String type, String level,
            List<Requestment> requestments, Company company, List<User> user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.workModel = workModel;
        this.type = type;
        this.level = level;
        this.requestments = requestments;
        this.company = company;
        this.user = user;
    }

    // getter setter

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

    public List<Requestment> getRequestments() {
        return this.requestments;
    }

    public void setRequestments(List<Requestment> requestments) {
        this.requestments = requestments;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<User> getUser() {
        return this.user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    // toString()

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", title='" + getTitle() + "'" +
                ", description='" + getDescription() + "'" +
                ", salary='" + getSalary() + "'" +
                ", workModel='" + getWorkModel() + "'" +
                ", type='" + getType() + "'" +
                ", level='" + getLevel() + "'" +
                ", requestments='" + getRequestments() + "'" +
                ", company='" + getCompany() + "'" +
                ", user='" + getUser() + "'" +
                "}";
    }

}
