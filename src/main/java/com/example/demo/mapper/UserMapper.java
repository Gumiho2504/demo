package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.Dto.UserDto;
import com.example.demo.model.User;

@Component
public class UserMapper {

    @Autowired
    ProfileMapper profileMapper;
    @Autowired
    JobMapper jobMapper;

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.createdAt(user.getCreatedAt());

        // job

        userDto.setSaveJobs(user.getSaveJobs() == null ? new ArrayList<>()
                : user.getSaveJobs().stream().map(jobMapper::toJobDto).collect(Collectors.toList()));

        // profile

        userDto.setProfile(user.getProfile() == null ? null : profileMapper.toProfileDto(user.getProfile()));

        return userDto;
    }

    public User toUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setCreatedAt(userDto.getCreatedAt());

        // job
        user.setSaveJobs(userDto.getSaveJobs() == null ? null
                : userDto.getSaveJobs().stream().map(jobMapper::toJob).collect(Collectors.toList()));

        // profile
        user.setProfile(userDto.getProfile() == null ? null : profileMapper.toProfile(userDto.getProfile()));

        return user;
    }
}
