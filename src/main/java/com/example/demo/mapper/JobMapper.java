package com.example.demo.mapper;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.Dto.JobDto;
import com.example.demo.Dto.RequestmentDto;
import com.example.demo.model.Job;

@Component
public class JobMapper {

    @Autowired
    CompanyMapper companyMapper;

    public JobDto toJobDto(Job job) {
        JobDto jobDto = new JobDto();
        jobDto.setId(job.getId());
        jobDto.setTitle(job.getTitle());
        jobDto.setCompany(companyMapper.toCompanyDto(job.getCompany()));
        jobDto.setDescription(job.getDescription());
        jobDto.setLevel(job.getLevel());
        jobDto.setType(job.getType());
        jobDto.setWorkModel(job.getWorkModel());
        jobDto.setSalary(job.getSalary());

        jobDto.setRequestments(job.getRequestments().stream()
                .map(requestment -> {
                    RequestmentDto requestmentDto = new RequestmentDto();
                    requestmentDto.setRequestment(requestment.getRequestment());
                    return requestmentDto;
                }).collect(Collectors.toList()));
        return jobDto;
    }

    public Job toJob(JobDto jobDto) {
        Job job = new Job();
        job.setId(jobDto.getId());
        job.setTitle(jobDto.getTitle());
        return job;
    }
}
