package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.EducationDto;
import com.example.demo.mapper.EducationMapper;
import com.example.demo.model.Education;
import com.example.demo.service.EducationService;

@Controller
@RestController
@RequestMapping("/education")
public class EducationController {

    @Autowired
    EducationService educationService;
    @Autowired
    EducationMapper educationMapper;

    @GetMapping("/")
    private List<EducationDto> getAllEducation() {
        return educationService.getAllEducation().stream().map(educationMapper::toEducationDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/id={id}")
    private ResponseEntity<EducationDto> getEducationById(@PathVariable long id) {
        Optional<Education> educationOptional = educationService.findEducationById(id);
        if (educationOptional.isPresent()) {
            EducationDto educationDto = educationMapper.toEducationDto(educationOptional.get());
            return new ResponseEntity<>(educationDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id={id}")
    private ResponseEntity<EducationDto> editEducation(@PathVariable long id, @RequestBody EducationDto education) {
        System.out.println(education);
        Optional<Education> educationOptional = educationService.editEducation(id,
                educationMapper.toEducation(education));
        if (educationOptional.isPresent()) {
            EducationDto educationDto = educationMapper.toEducationDto(educationOptional.get());
            return new ResponseEntity<>(educationDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/id={id}")
    private ResponseEntity<String> deleteEducation(@PathVariable long id) {
        educationService.DeleteEducation(id);
        return new ResponseEntity<>("education deleted", HttpStatus.ACCEPTED);
    }
}
