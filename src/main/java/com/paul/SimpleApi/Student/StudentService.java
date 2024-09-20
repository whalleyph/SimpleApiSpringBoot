package com.paul.SimpleApi.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Bean
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void postStudents(Student student) {
        studentRepository.save(student);
    }
}
