package com.example.demo.Dto;

public class CompanyInformationDto {
    private String information;

    public CompanyInformationDto() {
    }

    public CompanyInformationDto(String information) {
        this.information = information;
    }

    public String getInformation() {
        return this.information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return "{" +
                " information='" + getInformation() + "'" +
                "}";
    }

}
