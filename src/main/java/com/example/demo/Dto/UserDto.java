package com.example.demo.Dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class UserDto {
    private long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private List<JobDto> saveJobs;
    private ProfileDto profile;

    public UserDto() {
    }

    public UserDto(long id, String name, String email, String password, LocalDateTime createdAt, List<JobDto> saveJobs,
            ProfileDto profile) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.saveJobs = saveJobs;
        this.profile = profile;
    }

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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<JobDto> getSaveJobs() {
        return this.saveJobs;
    }

    public void setSaveJobs(List<JobDto> saveJobs) {
        this.saveJobs = saveJobs;
    }

    public ProfileDto getProfile() {
        return this.profile;
    }

    public void setProfile(ProfileDto profile) {
        this.profile = profile;
    }

    public UserDto id(long id) {
        setId(id);
        return this;
    }

    public UserDto name(String name) {
        setName(name);
        return this;
    }

    public UserDto email(String email) {
        setEmail(email);
        return this;
    }

    public UserDto password(String password) {
        setPassword(password);
        return this;
    }

    public UserDto createdAt(LocalDateTime createdAt) {
        setCreatedAt(createdAt);
        return this;
    }

    public UserDto saveJobs(List<JobDto> saveJobs) {
        setSaveJobs(saveJobs);
        return this;
    }

    public UserDto profile(ProfileDto profile) {
        setProfile(profile);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserDto)) {
            return false;
        }
        UserDto userDto = (UserDto) o;
        return id == userDto.id && Objects.equals(name, userDto.name) && Objects.equals(email, userDto.email)
                && Objects.equals(password, userDto.password) && Objects.equals(createdAt, userDto.createdAt)
                && Objects.equals(saveJobs, userDto.saveJobs) && Objects.equals(profile, userDto.profile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, createdAt, saveJobs, profile);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", email='" + getEmail() + "'" +
                ", password='" + getPassword() + "'" +
                ", createdAt='" + getCreatedAt() + "'" +
                ", saveJobs='" + getSaveJobs() + "'" +
                ", profile='" + getProfile() + "'" +
                "}";
    }

}
