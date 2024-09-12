package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.SkillRepository;
import com.example.demo.model.Skill;

import jakarta.transaction.Transactional;

@Service
public class SkillService {
    @Autowired
    SkillRepository skillRepository;

    public List<Skill> getAllSkill() {
        return skillRepository.findAll();
    }

    public Skill saveSkill(String title) {
        Skill tempSkill = new Skill();

        tempSkill.setTitle(title);
        tempSkill.setProfile(null);

        skillRepository.save(tempSkill);
        return tempSkill;
    }

    @Transactional
    public Skill editSkill(long id, String title) {
        Optional<Skill> skillOptional = skillRepository.findById(id);
        Skill skill = skillOptional.get();
        skill.setTitle(title);
        return skill;
    }

    @Transactional
    public void deleteSkill(long id) {
        Optional<Skill> skillOptional = skillRepository.findById(id);
        skillOptional.get().setProfile(null);
        skillRepository.delete(skillOptional.get());
    }

}
