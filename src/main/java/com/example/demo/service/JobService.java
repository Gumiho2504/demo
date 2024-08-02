package com.example.demo.service;


import java.util.List;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Dto.JobDto;
import com.example.demo.Repository.JobRepository;

@Service
public class JobService {

    @Autowired
    JobRepository jobRepository;


    public List<JobDto> getAllJobs(){
        return jobRepository.findAll().stream()
        .map(job -> {
        JobDto jobDto = new JobDto();
        jobDto.setId(job.getId());
        jobDto.setTitle(job.getTitle());
        return jobDto;
        }).collect(Collectors.toList()
        );
    }
    
}
