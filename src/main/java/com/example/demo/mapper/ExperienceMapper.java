package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.Dto.ExperienceDto;
import com.example.demo.model.Experience;

@Component
public class ExperienceMapper {

    public ExperienceDto toExperienceDto(Experience experience) {
        ExperienceDto experienceDto = new ExperienceDto();
        experienceDto.setId(experience.getId());
        experienceDto.setTitle(experience.getTitle());
        experienceDto.setCompany(experience.getCompany());
        experienceDto.setDescription(experience.getDescription());
        experienceDto.setStartDate(experience.getStartDate());
        experienceDto.setEndDate(experience.getEndDate());
        experienceDto.setIsWorking(experience.getIsWorking());
        return experienceDto;
    }

    public Experience toExperience(ExperienceDto experienceDto) {
        Experience experience = new Experience();
        experience.setId(experienceDto.getId());
        experience.setTitle(experienceDto.getTitle());
        experience.setDescription(experienceDto.getDescription());
        experience.setCompany(experienceDto.getCompany());
        experience.setStartDate(experienceDto.getStartDate());
        experience.setEndDate(experienceDto.getEndDate());
        experience.setIsWorking(experienceDto.getIsWorking());
        return experience;
    }
}
