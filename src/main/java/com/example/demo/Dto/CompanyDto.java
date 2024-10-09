package com.example.demo.Dto;

import java.util.List;

public class CompanyDto {
    private long id;
    private String name;
    private String description;
    private String phoneNumber;
    private String email;
    private List<CompanyInformationDto> informations;

    // constructor

    public CompanyDto() {
    }

    public CompanyDto(long id, String name, String description, String phoneNumber, String email,
            List<CompanyInformationDto> informations) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.informations = informations;
    }

    // getter setter

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CompanyInformationDto> getInformations() {
        return this.informations;
    }

    public void setInformations(List<CompanyInformationDto> informations) {
        this.informations = informations;
    }

    // toString()

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", description='" + getDescription() + "'" +
                ", phoneNumber='" + getPhoneNumber() + "'" +
                ", email='" + getEmail() + "'" +
                ", informations='" + getInformations() + "'" +
                "}";
    }

}
