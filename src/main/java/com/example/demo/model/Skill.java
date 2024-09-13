package com.example.demo.model;

import java.util.List;

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
import jakarta.persistence.Table;

@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinTable(name = "profile_skill", // Join table name
            joinColumns = @JoinColumn(name = "skill_id"), // Column for Student
            inverseJoinColumns = @JoinColumn(name = "profile_id") // Column for Course
    )
    private List<Profile> profile;

    // constructor

    public Skill() {
    }

    public Skill(long id, String title, List<Profile> profile) {
        this.id = id;
        this.title = title;
        this.profile = profile;
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

    public List<Profile> getProfile() {
        return this.profile;
    }

    public void setProfile(List<Profile> profile) {
        this.profile = profile;
    }

    // toString

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", title='" + getTitle() + "'" +
                ", profile='" + getProfile() + "'" +
                "}";
    }

}
