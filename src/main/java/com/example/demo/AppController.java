package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.EducationDto;
import com.example.demo.Dto.ExperienceDto;
import com.example.demo.Dto.JobDto;
import com.example.demo.Dto.UserDto;
import com.example.demo.mapper.EducationMapper;
import com.example.demo.mapper.ExperienceMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Profile;
import com.example.demo.model.User;
import com.example.demo.service.JobService;
import com.example.demo.service.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class AppController {

    @Autowired
    JobService jobService;

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ExperienceMapper experienceMapper;

    @Autowired
    EducationMapper educationMapper;

    @GetMapping("/")
    public List<JobDto> getAllJob() {
        return jobService.getAllJobs();
    }

    @GetMapping("/user/")
    public List<UserDto> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/user/{id}")
    public Optional<UserDto> getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/user/register")
    public ResponseEntity<User> saveUser(@RequestBody UserDto userDto) {
        try {
            User createdUser = userService.saveUser(userDto);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable long userId, @RequestBody UserDto userDto) {
        Optional<User> userOptional = userService.updateUser(userId, userDto);
        if (userOptional.isPresent()) {
            return new ResponseEntity<>(userOptional.get(), HttpStatusCode.valueOf(200));
        } else {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }

    }

    // @PostMapping("/user/register")
    // public ResponseEntity<?> registerUser(@RequestBody User user) {
    // try {
    // User newUser = userService.registerUser(user.getName(), user.getEmail(),
    // user.getPassword());
    // return ResponseEntity.ok(newUser);
    // } catch (Exception e) {
    // return ResponseEntity.badRequest().body("user name already use");
    // }
    // }

    @PostMapping("/user/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody UserDto userDto) {
        UserDto loggedInUser = userService.loginUser(userDto.getEmail(), userDto.getPassword());
        if (loggedInUser != null) {
            return new ResponseEntity<>(loggedInUser, HttpStatusCode.valueOf(200));
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    // user and Job ############################ //

    @PostMapping("/user/{userId}/savejob={jobId}")
    public ResponseEntity<UserDto> addJobToUser(@PathVariable Long userId, @PathVariable Long jobId) {
        Optional<User> userOptional = userService.addSaveJobsToUser(userId, jobId);

        if (userOptional.isPresent()) {
            return new ResponseEntity<>(userMapper.toUserDto(userOptional.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/{userId}/removejob={jobId}")
    public ResponseEntity<UserDto> removeJobsFromUser(@PathVariable Long userId, @PathVariable Long jobId) {
        Optional<User> userOptional = userService.removeSaveJobsFromUser(userId, jobId);
        if (userOptional.isPresent()) {
            return new ResponseEntity<>(userMapper.toUserDto(userOptional.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // User and Profile############################ //

    @PostMapping("/user/profile/{id}")
    public ResponseEntity<UserDto> addProfileToUser(@PathVariable long id, @RequestBody UserDto userDto) {
        // System.out.println("user - " + userDto);
        User tUser = userMapper.toUser(userDto);

        // Check if the profile in the DTO is null
        Profile profile = tUser.getProfile();
        if (profile == null) {
            System.out.println("null");

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Return 400 if profile is null
        }

        // Add the profile to the user and save it
        User updatedUser = userService.addProfileToUser(id, profile);

        return new ResponseEntity<>(userMapper.toUserDto(updatedUser), HttpStatus.CREATED);
    }

    // user at skill############################ //

    @PostMapping("/user/{userId}/skill/{skillId}")
    public ResponseEntity<UserDto> addSkillToUser(@PathVariable Long userId, @PathVariable Long skillId) {
        System.out.println("controller");
        Optional<User> userOptional = userService.addSkillToUser(userId, skillId);
        if (userOptional.isPresent()) {

            UserDto userDto = new UserDto();
            User user = userOptional.get();
            userDto = userMapper.toUserDto(user);
            return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/user/{userId}/skill/{skillId}")
    public ResponseEntity<UserDto> deleteSkillFromUser(@PathVariable Long userId, @PathVariable Long skillId) {
        Optional<User> userOptional = userService.deleteSkillFromUser(userId, skillId);
        if (userOptional.isPresent()) {
            UserDto userDto = userMapper.toUserDto(userOptional.get());
            return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // experience############################ //

    @PostMapping("/user/{id}/experience/post")
    private ResponseEntity<UserDto> addExperience(@PathVariable long id, @RequestBody ExperienceDto experience) {
        Optional<User> userOptional = userService.addExperience(id, experienceMapper.toExperience(experience));
        if (userOptional.isPresent()) {
            UserDto userDto = userMapper.toUserDto(userOptional.get());
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // education ############################ //

    @PostMapping("/user/{id}/education/post")
    private ResponseEntity<UserDto> addEducation(@PathVariable long id, @RequestBody EducationDto education) {
        Optional<User> userOptional = userService.addEducation(id, educationMapper.toEducation(education));
        if (userOptional.isPresent()) {
            UserDto userDto = userMapper.toUserDto(userOptional.get());
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}// end of class
