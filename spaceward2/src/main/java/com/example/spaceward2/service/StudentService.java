package com.example.spaceward2.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.spaceward2.model.Student;
import com.example.spaceward2.repository.StudentRepository;



@Service
public class StudentService {

    private final StudentRepository repository;

    @Autowired
    public StudentService(final StudentRepository repository) {
        this.repository = repository;
    }

    public Page<Student> getStudents(final int pageNumber, final int size) {
        return repository.findAll(PageRequest.of(pageNumber, size));
    }

    public Optional<Student> getStudent(final UUID studentNo) {
        return repository.findById(studentNo);
    }

    public Student saveStudent(final Student student) {
        return repository.save(student);
    }

    public void deleteStudent(final UUID studentNo) {
        repository.deleteById(studentNo);
    }
}
