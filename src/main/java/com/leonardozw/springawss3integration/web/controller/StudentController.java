package com.leonardozw.springawss3integration.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.leonardozw.springawss3integration.domain.StudentService;
import com.leonardozw.springawss3integration.web.dto.StudentReq;
import com.leonardozw.springawss3integration.web.dto.StudentRes;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/post")
    public ResponseEntity<StudentRes> post(@RequestBody StudentReq studentReq){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.create(studentReq));
    }

    @GetMapping("/list")
    public ResponseEntity<List<StudentRes>> list(){
        return ResponseEntity.ok(studentService.list());
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<StudentRes> upload(@PathVariable UUID id, @RequestParam("file") MultipartFile file) throws IOException{
        return ResponseEntity.ok(studentService.upload(file, id));
    }
}
