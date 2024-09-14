package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.Dto.EducationDto;
import com.example.demo.model.Education;

@Component
public class EducationMapper {

    public EducationDto toEducationDto(Education education) {
        EducationDto educationDto = new EducationDto();
        educationDto.setId(education.getId());
        educationDto.setSchool(education.getSchool());
        educationDto.setField(education.getField());
        educationDto.setStartDate(education.getStartDate());
        educationDto.setEndDate(education.getEndDate());
        educationDto.setDescription(education.getDescription());
        return educationDto;
    }

    public Education toEducation(EducationDto educationDto) {
        Education education = new Education();
        education.setId(educationDto.getId());
        education.setSchool(educationDto.getSchool());
        education.setField(educationDto.getField());
        education.setStartDate(educationDto.getStartDate());
        education.setEndDate(educationDto.getEndDate());
        education.setDescription(educationDto.getDescription());
        return education;
    }
}
