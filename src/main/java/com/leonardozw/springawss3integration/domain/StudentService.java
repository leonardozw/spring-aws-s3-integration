package com.leonardozw.springawss3integration.domain;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.leonardozw.springawss3integration.aws.AwsS3Service;
import com.leonardozw.springawss3integration.web.dto.StudentReq;
import com.leonardozw.springawss3integration.web.dto.StudentRes;

@Service
public class StudentService {

    private static final String BUCKET_NAME = "aluno-img-bucket";

    private final AwsS3Service awsS3Service;

    private final StudentRepository studentRepository;

    public StudentService(AwsS3Service awsS3Service, StudentRepository studentRepository) {
        this.awsS3Service = awsS3Service;
        this.studentRepository = studentRepository;
    }

    public StudentRes create(StudentReq studentReq) {
        Student student = toStudent(studentReq);
        student.setId(UUID.randomUUID());
        studentRepository.save(student);
        return toStudentRes(student);
    }

    public StudentRes upload(MultipartFile file, UUID id) throws IOException{

        Optional<Student> studentOpt = studentRepository.findById(id);
        if(studentOpt.isPresent()){
            Student student = studentOpt.get();
            String imageUrl = awsS3Service.uploadImage(file, id, BUCKET_NAME);
            student.setImageUrl(imageUrl);
            studentRepository.save(student);
            return toStudentRes(student);
        }else{
            throw new RuntimeException("Student not found");
        }

    }

    public List<StudentRes> list() {
        Iterable<Student> studentsIt = studentRepository.findAll();
        List<Student> studentsList = (List<Student>) studentsIt;
        return studentsList.stream().map(student -> toStudentRes(student)).toList();
    }

    private Student toStudent(StudentReq studentReq) {
        return new Student(studentReq.name(), studentReq.email());
    }

    private StudentRes toStudentRes(Student student) {
        return new StudentRes(student.getId().toString(), student.getName(), student.getEmail(), student.getImageUrl());
    }
}
