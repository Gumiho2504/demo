package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.ExperienceRepository;
import com.example.demo.model.Experience;

@Service
public class ExperienceService {
    @Autowired
    ExperienceRepository experienceRepository;

    public List<Experience> findAllExperience() {
        return experienceRepository.findAll();
    }

    public Optional<Experience> findExperienceById(Long id) {
        return experienceRepository.findById(id);
    }

    public Optional<Experience> editExperience(Long id, Experience experience) {
        Optional<Experience> optionalExperience = findExperienceById(id);
        Experience tempExperience = optionalExperience.get();
        tempExperience.setDescription(experience.getDescription());
        tempExperience.setCompany(experience.getCompany());
        tempExperience.setStartDate(experience.getStartDate());
        tempExperience.setEndDate(experience.getEndDate());
        tempExperience.setTitle(experience.getTitle());
        experienceRepository.save(tempExperience);
        return optionalExperience;
    }

    public void deleteExperience(Long id) {
        Optional<Experience> optionalExperience = experienceRepository.findById(id);
        experienceRepository.delete(optionalExperience.get());
    }

}
