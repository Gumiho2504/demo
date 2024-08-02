package com.example.demo.service;


import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.Job;
import com.example.demo.User;
import com.example.demo.Dto.JobDto;
import com.example.demo.Dto.UserDto;
import com.example.demo.Repository.JobRepository;
import com.example.demo.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    JobRepository jobRepository;

    public List<UserDto> getAllUser(){
        return userRepository.findAll().stream()
        .map(this::toUserDto).toList();
    }

    public Optional<UserDto> getUserById(long id){
        Optional<User> user = userRepository.findById(id);
        return user.map(this::toUserDto);
       
    }

    @Transactional
    public User saveUser(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        if(user.getSaveJobs() != null){
            List<Job> jobs = userDto.getSaveJob().stream().map(jobDto->{
              Job job = new Job();
              job.setTitle(jobDto.getTitle());
              return job;
            }).collect(Collectors.toList());
            user.setSaveJobs(jobs);
        }
        return userRepository.save(user);
    }


    @Transactional
    public Optional<User> addSaveJobsToUser(long userId, long jobId){
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        if(userOptional.isPresent() && jobOptional.isPresent()){
            User user = userOptional.get();
            Job job = jobOptional.get();
            user.addJob(job);
            userRepository.save(user);

        }
        return userOptional;
    }

    @Transactional
    public Optional<User> removeSaveJobsFromUser(long userId,long jobId){
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        if(userOptional.isPresent()&& jobOptional.isPresent()){
            User user = userOptional.get();
            Job job = jobOptional.get();
            user.removeJob(job);

            user.getSaveJobs().forEach(j -> System.out.println(j.getTitle()));


            userRepository.save(user);
            jobRepository.save(job);
        }
        return userOptional;
    }

    

    @Transactional
    public void deleteUser(long id){
        userRepository.deleteById(id);
    }


    @Transactional
    public Optional<User> updateUser(Long userId,UserDto userDto){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setName(userDto.getName());
            // if(userDto.getSaveJob() != null){
            //     List<Job> jobs = userDto.getSaveJob().stream()
            //     .map(jobDto -> {
            //         Job job = new Job();
            //         job.setId(jobDto.getId());
            //         job.setTitle(jobDto.getTitle());
            //         return job;
            //     }).collect(Collectors.toList());
            //     user.setSaveJobs(jobs);
            // }
            
            userRepository.save(user);
        }
        return userOptional;
    }



    public UserDto toUserDto(User user){
        UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            
            userDto.setSaveJob(user.getSaveJobs().stream()
            .map(job -> {
                JobDto jobDto = new JobDto();
                jobDto.setId(job.getId());
                jobDto.setTitle(job.getTitle());
                return jobDto;
            }).collect(Collectors.toList())
            );
        
            return userDto;
    }
}
