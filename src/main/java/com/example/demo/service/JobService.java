package com.example.demo.service;

import java.util.List;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Dto.JobDto;
import com.example.demo.Repository.JobRepository;
import com.example.demo.mapper.JobMapper;

@Service
public class JobService {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    JobMapper jobMapper;

    public List<JobDto> getAllJobs() {
        return jobRepository.findAll().stream()
                .map(jobMapper::toJobDto).collect(Collectors.toList());
    }

}
