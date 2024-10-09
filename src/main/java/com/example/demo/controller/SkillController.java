package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.example.demo.Dto.SkillDto;
import com.example.demo.mapper.SkillMapper;
import com.example.demo.model.Skill;
import com.example.demo.service.SkillService;

@Controller
@RestController
@RequestMapping("/skill")
public class SkillController {
    @Autowired
    SkillService skillService;
    @Autowired
    SkillMapper skillMapper;

    @GetMapping("/")
    public List<SkillDto> getAllSkill() {
        return skillService.getAllSkill().stream().map(skillMapper::toSkillDto).collect(Collectors.toList());
    }

    @PostMapping("/")
    public ResponseEntity<SkillDto> postSkill(@RequestBody Skill skill) {
        SkillDto temSkillDto = new SkillDto();
        temSkillDto = skillMapper.toSkillDto(skillService.saveSkill(skill.getTitle()));
        return new ResponseEntity<>(temSkillDto, HttpStatus.CREATED);
    }

    @PutExchange("/id={id}")
    public ResponseEntity<SkillDto> editSkill(@PathVariable long id, @RequestBody Skill skill) {
        SkillDto tempSkillDto = new SkillDto();
        tempSkillDto = skillMapper.toSkillDto(skillService.editSkill(id, skill.getTitle()));
        return new ResponseEntity<>(tempSkillDto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/id={id}")
    public ResponseEntity<String> deleteSkill(@PathVariable long id) {
        skillService.deleteSkill(id);
        return new ResponseEntity<>("deleted", HttpStatus.ACCEPTED);
    }

}
