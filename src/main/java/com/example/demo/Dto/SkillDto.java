package com.example.demo.Dto;

import org.springframework.stereotype.Component;

import com.example.demo.model.Skill;

@Component
public class SkillDto {
    private long id;
    private String title;

    // method

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

    // constructor

    public SkillDto() {
    }

    public SkillDto(long id, String title) {
        this.id = id;
        this.title = title;
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

    // toString()

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", title='" + getTitle() + "'" +
                "}";
    }

}
