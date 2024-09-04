package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.JobDto;
import com.example.demo.Dto.UserDto;
import com.example.demo.service.JobService;
import com.example.demo.service.UserService;

import java.net.http.HttpResponse;
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

    // @GetMapping("/")
    // public String sayHello(){
    //     return "Hello";
    // }

    @GetMapping("/")
    public List<JobDto> getAllJob(){
        
        return jobService.getAllJobs();
    }
    
    @GetMapping("/user/")
    public List<UserDto> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/user/{id}")
   public Optional<UserDto> getUserById(@PathVariable long id){
    return userService.getUserById(id);
   }

   @PostMapping("/user/")
    public ResponseEntity<User> saveUser(@RequestBody UserDto userDto) {
        try{
            User createdUser = userService.saveUser(userDto);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }catch(Exception e){
            return new  ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
       
        
    }
   
    @PostMapping("/user/{userId}/savejob={jobId}")
    public ResponseEntity<User> addJobToUser(@PathVariable Long userId, @PathVariable Long jobId) {
        Optional<User> userOptional = userService.addSaveJobsToUser(userId, jobId);

        if (userOptional.isPresent()) {
            return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/{userId}/removejob={jobId}")
    public ResponseEntity<User> removeJobsFromUser(@PathVariable Long userId, @PathVariable Long jobId){
        Optional<User> userOptional = userService.removeSaveJobsFromUser(userId, jobId);
        if(userOptional.isPresent()){
            return new ResponseEntity<>(userOptional.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/user/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable long userId, @RequestBody UserDto userDto){
        Optional<User> userOptional = userService.updateUser(userId, userDto);
        if(userOptional.isPresent()){
            return new ResponseEntity<>(userOptional.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }

    }
    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User newUser = userService.registerUser(user.getName(), user.getEmail(), user.getPassword());
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("user name already use");
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        User loggedInUser = userService.loginUser(user.getEmail(), user.getPassword());
        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }
    
}
