package com.example.demo.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.Dto.CompanyDto;
import com.example.demo.Dto.CompanyInformationDto;
import com.example.demo.model.Company;

@Component
public class CompanyMapper {

    public CompanyDto toCompanyDto(Company company) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(company.getId());
        companyDto.setName(company.getName());
        companyDto.setDescription(company.getDescription());
        companyDto.setEmail(company.getEmail());
        companyDto.setPhoneNumber(company.getPhoneNumber());

        companyDto.setInformations(company.getInformations()
                .stream()
                .map(information -> {
                    CompanyInformationDto informationDto = new CompanyInformationDto();
                    informationDto.setInformation(information.getInformation());
                    return informationDto;
                }).collect(Collectors.toList()));
        return companyDto;
    }
}
