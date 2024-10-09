package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CompanyInformation;

@Repository
public interface CompanyInfoRepository extends JpaRepository<CompanyInformation, Long> {

}
