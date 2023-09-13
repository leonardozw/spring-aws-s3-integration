package com.leonardozw.springawss3integration.domain;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, UUID>{
    
}
