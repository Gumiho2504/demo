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

import com.example.demo.Dto.ExperienceDto;
import com.example.demo.mapper.ExperienceMapper;
import com.example.demo.model.Experience;
import com.example.demo.service.ExperienceService;

@Controller
@RestController
@RequestMapping("/experience")
public class ExperienceController {
    @Autowired
    ExperienceService experienceService;

    @Autowired
    ExperienceMapper experienceMapper;

    @GetMapping("/")
    public List<ExperienceDto> findAllExperience() {
        return experienceService.findAllExperience().stream().map(experienceMapper::toExperienceDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/id={id}")
    private ResponseEntity<ExperienceDto> findExperienceById(@PathVariable Long id) {
        Optional<Experience> experienceOptional = experienceService.findExperienceById(id);
        if (experienceOptional.isPresent()) {
            return new ResponseEntity<>(experienceMapper.toExperienceDto(experienceOptional.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id={id}")
    private ResponseEntity<ExperienceDto> editExperience(@PathVariable long id, @RequestBody ExperienceDto experience) {
        Optional<Experience> experienceOptional = experienceService.editExperience(id,
                experienceMapper.toExperience(experience));
        if (experienceOptional.isPresent()) {
            return new ResponseEntity<>(experienceMapper.toExperienceDto(experienceOptional.get()),
                    HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/id={id}")
    private ResponseEntity<String> deleteExperience(@PathVariable Long id) {
        experienceService.deleteExperience(id);
        return new ResponseEntity<>("deleted", HttpStatus.ACCEPTED);
    }
}
