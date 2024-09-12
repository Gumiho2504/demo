package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.Dto.UserDto;
import com.example.demo.Repository.JobRepository;
import com.example.demo.Repository.ProfileRepository;
import com.example.demo.Repository.SkillRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Job;
import com.example.demo.model.Profile;
import com.example.demo.model.Skill;
import com.example.demo.model.User;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    UserMapper userMapper;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserDto).toList();
    }

    public Optional<UserDto> getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapper::toUserDto);
    }

    @Transactional
    public User saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setCreatedAt(LocalDateTime.now());
        if (user.getSaveJobs() != null) {
            List<Job> jobs = userDto.getSaveJobs().stream().map(jobDto -> {
                Job job = new Job();
                job.setTitle(jobDto.getTitle());
                return job;
            }).collect(Collectors.toList());
            user.setSaveJobs(jobs);
        } else {
            user.setSaveJobs(new ArrayList<>());
        }
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public Optional<User> updateUser(Long userId, UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());

            userRepository.save(user);
        }
        return userOptional;
    }

    @Transactional
    public User registerUser(String name, String email, String password) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setSaveJobs(null);
        return userRepository.save(newUser);
    }

    @Transactional
    public UserDto loginUser(String email, String password) {
        User tempUser = userRepository.findUserByEmail(email);
        UserDto userDto = new UserDto();
        userDto = userMapper.toUserDto(tempUser);
        // System.out.println(tempUser.getPassword() + tempUser.getName());
        if (tempUser != null && tempUser.getPassword().matches(password)) {
            System.out.println("fond!");
            return userDto;
        }
        /// System.out.println("not fond!");
        // System.out.println(tempUser.getPassword() + tempUser.getName());
        return null;

    }

    // user save job

    @Transactional
    public Optional<User> addSaveJobsToUser(long userId, long jobId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        if (userOptional.isPresent() && jobOptional.isPresent()) {
            User user = userOptional.get();
            Job job = jobOptional.get();
            user.addJob(job);
            userRepository.save(user);

        }
        return userOptional;
    }

    @Transactional
    public Optional<User> removeSaveJobsFromUser(long userId, long jobId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        if (userOptional.isPresent() && jobOptional.isPresent()) {
            User user = userOptional.get();
            Job job = jobOptional.get();
            user.removeJob(job);

            // user.getSaveJobs().forEach(j -> System.out.println(j.getTitle()));

            userRepository.save(user);
            jobRepository.save(job);
        }
        return userOptional;
    }

    // user at Profile

    @Transactional
    public User addProfileToUser(long id, Profile profile) {
        // Fetch user by id
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new EntityNotFoundException("User not found with id: " + id); // Handle user not found
        }

        User user = userOptional.get();

        // Set the profile for the user
        profile = profileRepository.save(profile); // Save the profile first
        user.setProfile(profile); // Associate the profile with the user

        // Save the updated user with the profile
        userRepository.save(user);

        return user;
    }

    // user at skill

    @Transactional
    public Optional<User> addSkillToUser(Long userId, Long skillId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Skill> skillOptional = skillRepository.findById(skillId);
        // System.out.println("find");
        if (userOptional.isPresent() && skillOptional.isPresent()) {
            User user = userOptional.get();
            Skill skill = skillOptional.get();
            user.getProfile().addSkill(skill);

            userRepository.save(user);
            skillRepository.save(skill);
            // System.out.println(skill.getTitle());
            // System.out.println(user);
            // return userOptional;
        }

        return userOptional;
    }

    @Transactional
    public Optional<User> deleteSkillFromUser(Long userId, Long skillId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Skill> skillOptional = skillRepository.findById(skillId);
        if (userOptional.isPresent() && skillOptional.isPresent()) {
            User user = userOptional.get();
            Skill skill = skillOptional.get();
            user.getProfile().deleteSkill(skill);
            userRepository.save(user);
            skillRepository.save(skill);
        }
        return userOptional;
    }

}
