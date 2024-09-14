package com.example.demo.Dto;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.example.demo.model.Profile;

@Component
public class ProfileDto {
    private long id;
    private String title;
    private String phoneNumber;
    private List<SkillDto> skills;
    private List<EducationDto> educations;
    private List<ExperienceDto> experiences;

    public ProfileDto toProfileDto(Profile profile) {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setId(profile.getId());
        profileDto.setTitle(profile.getTitle());
        profileDto.setPhoneNumber(profile.getPhoneNumber());

        profileDto.setSkills(profile.getSkills()
                .stream()
                .map(skill -> {
                    SkillDto skillDto = new SkillDto();
                    skillDto.setId(skill.getId());
                    skillDto.setTitle(skill.getTitle());
                    return skillDto;
                }).collect(Collectors.toList()));

        return profileDto;
    }

    // construtor

    public ProfileDto() {
    }

    public ProfileDto(long id, String title, String phoneNumber, List<SkillDto> skills, List<EducationDto> educations,
            List<ExperienceDto> experiences) {
        this.id = id;
        this.title = title;
        this.phoneNumber = phoneNumber;
        this.skills = skills;
        this.educations = educations;
        this.experiences = experiences;
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

    public List<SkillDto> getSkills() {
        return this.skills;
    }

    public void setSkills(List<SkillDto> skill) {
        this.skills = skill;
    }

    public List<EducationDto> getEducations() {
        return this.educations;
    }

    public void setEducations(List<EducationDto> educations) {
        this.educations = educations;
    }

    public List<ExperienceDto> getExperiences() {
        return this.experiences;
    }

    public void setExperiences(List<ExperienceDto> experiences) {
        this.experiences = experiences;
    }

    // toString()

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", title='" + getTitle() + "'" +
                ", phoneNumber='" + getPhoneNumber() + "'" +
                ", skills='" + getSkills() + "'" +
                ", educations='" + getEducations() + "'" +
                ", experiences='" + getExperiences() + "'" +
                "}";
    }

}
