package com.paul.SimpleApi.Student;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void postStudent(Student student) {
        Optional<Student> studentOptionalEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptionalEmail.isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean studentExists = studentRepository.existsById(studentId);
        if (!studentExists) {
            throw new IllegalArgumentException("Student does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudentNameAndEmail(Long studentId, UpdateStudentDTO updateStudentDTO) {
        boolean studentExists = studentRepository.existsById(studentId);
        if (!studentExists) {
            throw new IllegalArgumentException("Student does not exist");
        }
        Student studentToUpdate = studentRepository.findById(studentId).get();
        studentToUpdate.setName(updateStudentDTO.getName());
        studentToUpdate.setEmail(updateStudentDTO.getEmail());
    }
}
