package com.example.demo.mapper;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.Dto.ProfileDto;
import com.example.demo.Dto.SkillDto;
import com.example.demo.model.Profile;
import com.example.demo.model.Skill;

@Component
public class ProfileMapper {

    @Autowired
    SkillMapper skillMapper;

    public ProfileDto toProfileDto(Profile profile) {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setId(profile.getId());
        profileDto.setTitle(profile.getTitle());
        profileDto.setPhoneNumber(profile.getPhoneNumber());

        profileDto.setSkills(
                profile.getSkills() == null ? null
                        : profile.getSkills().stream().map(skillMapper::toSkillDto).collect(Collectors.toList()));
        return profileDto;
    }

    public Profile toProfile(ProfileDto profileDto) {
        Profile profile = new Profile();
        profile.setId(profileDto.getId());
        profile.setTitle(profileDto.getTitle());
        profile.setPhoneNumber(profileDto.getPhoneNumber());

        profile.setSkills(
                profileDto.getSkills() == null ? null
                        : profileDto.getSkills().stream().map(skillMapper::toSkill).collect(Collectors.toList()));

        return profile;
    }
}
