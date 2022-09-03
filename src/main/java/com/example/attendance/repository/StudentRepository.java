package com.example.attendance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.attendance.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    
}
