package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.EducationRepository;
import com.example.demo.model.Education;

@Service
public class EducationService {

    @Autowired
    EducationRepository educationRepository;

    public List<Education> getAllEducation() {
        return educationRepository.findAll();
    }

    public Optional<Education> findEducationById(Long id) {
        return educationRepository.findById(id);
    }

    public Optional<Education> editEducation(long id, Education education) {
        Optional<Education> educationOptional = findEducationById(id);
        Education tempEducation = new Education();
        tempEducation = education;
        educationRepository.save(tempEducation);
        return educationOptional;
    }

    public void DeleteEducation(Long id) {
        Optional<Education> eduOptional = findEducationById(id);
        educationRepository.delete(eduOptional.get());
    }
}
