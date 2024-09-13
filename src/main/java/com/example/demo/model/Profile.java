package com.example.demo.model;

import java.util.ArrayList;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String phoneNumber;
    // add @OneToOne annotation
    @OneToOne(mappedBy = "profile", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH })
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinTable(name = "profile_skill", // Join table name
            joinColumns = @JoinColumn(name = "profile_id"), // Column for Student
            inverseJoinColumns = @JoinColumn(name = "skill_id") // Column for Course
    )
    private List<Skill> skills;

    public void addSkill(Skill skill) {
        if (skills == null) {
            skills = new ArrayList<>();
        } else {
            skills.add(skill);
        }
    }

    public void deleteSkill(Skill skill) {
        skills.remove(skill);
    }

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Education> educations;

    public void addEduction(Education education) {
        if (educations == null) {
            educations = new ArrayList<>();
        } else {
            educations.add(education);
        }
    }

    public void removeEducation(Education education) {
        educations.remove(education);
    }

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Experience> experiences;

    public void addExperience(Experience experience) {
        if (experiences == null) {
            experiences = new ArrayList<>();
        } else {
            experiences.add(experience);
        }
    }

    public void removeExperience(Experience experience) {
        experiences.remove(experience);
    }

    // constructor

    public Profile() {

    }

    public Profile(long id, String title, String phoneNumber, User user, List<Skill> skills) {
        this.id = id;
        this.title = title;
        this.phoneNumber = phoneNumber;
        this.user = user;
        this.skills = skills;
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

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Skill> getSkills() {
        return this.skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Education> getEducations() {
        return this.educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public List<Experience> getExperiences() {
        return this.experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    // toString

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", title='" + getTitle() + "'" +
                ", phoneNumber='" + getPhoneNumber() + "'" +
                ", user='" + getUser() + "'" +
                ", skills='" + getSkills() + "'" +
                ", educations='" + getEducations() + "'" +
                ", experiences='" + getExperiences() + "'" +
                "}";
    }

}
