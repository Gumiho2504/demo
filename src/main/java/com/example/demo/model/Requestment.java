
package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "requestment")
public class Requestment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String requestment;

    @ManyToOne()
    @JoinColumn(name = "job_id")
    private Job job;

    // constructor

    public Requestment() {
    }

    public Requestment(long id, String requestment, Job job) {
        this.id = id;
        this.requestment = requestment;
        this.job = job;
    }

    // getter setter

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRequestment() {
        return this.requestment;
    }

    public void setRequestment(String requestment) {
        this.requestment = requestment;
    }

    public Job getJob() {
        return this.job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    // toString()

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", requestment='" + getRequestment() + "'" +
                ", job='" + getJob() + "'" +
                "}";
    }

}