package com.example.attendance.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.attendance.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    // Page<Teacher> findAllTeacher(Pageable pageable);
}
