package com.example.attendance.service.teacher;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.example.attendance.model.Teacher;
import com.example.attendance.service.IGeneralService;

public interface ITeacherService extends IGeneralService<Teacher> {
    // Page<Teacher> findAllTeacher(Pageable pageable);
}
