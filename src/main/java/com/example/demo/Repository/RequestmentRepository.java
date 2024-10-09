package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Requestment;

@Repository
public interface RequestmentRepository extends JpaRepository<Requestment, Long> {

}
