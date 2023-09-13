package com.leonardozw.springawss3integration.domain;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "students")
public class Student {
    
    @Id
    private UUID id;
    private String name;
    private String email;
    private String imageUrl;

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
