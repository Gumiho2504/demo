package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.Dto.SkillDto;
import com.example.demo.model.Skill;

@Component
public class SkillMapper {

    public SkillDto toSkillDto(Skill skill) {
        SkillDto skillDto = new SkillDto();
        skillDto.setId(skill.getId());
        skillDto.setTitle(skill.getTitle());
        return skillDto;
    }

    public Skill toSkill(SkillDto skillDto) {
        Skill skill = new Skill();
        skill.setId(skillDto.getId());
        skill.setTitle(skillDto.getTitle());
        return skill;
    }
}
