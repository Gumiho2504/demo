package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Education;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {

}
