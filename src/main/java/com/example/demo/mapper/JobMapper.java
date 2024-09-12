package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.Dto.JobDto;
import com.example.demo.model.Job;

@Component
public class JobMapper {

    public JobDto toJobDto(Job job) {
        JobDto jobDto = new JobDto();
        jobDto.setId(job.getId());
        jobDto.setTitle(job.getTitle());
        return jobDto;
    }

    public Job toJob(JobDto jobDto) {
        Job job = new Job();
        job.setId(jobDto.getId());
        job.setTitle(jobDto.getTitle());
        return job;
    }
}
