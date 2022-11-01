package com.example.attendance.service.teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.attendance.model.Teacher;
import com.example.attendance.repository.TeacherRepository;

@Service
public class TeacherService implements ITeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Iterable<Teacher> findAll() {
        // TODO Auto-generated method stub
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        // TODO Auto-generated method stub
        return teacherRepository.findById(id);
    }

    @Override
    public Teacher save(Teacher teacher) {
        // TODO Auto-generated method stub
        return teacherRepository.save(teacher);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        teacherRepository.deleteById(id);
    }

    @Override
    public Teacher update(Optional<Teacher> cur, Teacher teacher) {
        // TODO Auto-generated method stub
        teacher.setId(cur.get().getId());
        if (teacher.getFirstName() == null || teacher.getFirstName().trim().equals("")) {
            teacher.setFirstName(cur.get().getFirstName());
        }
        if (teacher.getLastName() == null || teacher.getLastName().trim().equals("")) {
            teacher.setLastName(cur.get().getLastName());
        }
        if (teacher.getAddress() == null || teacher.getAddress().trim().equals("")) {
            teacher.setAddress(cur.get().getAddress());
        }
        if (teacher.getBirthday() == null || teacher.getBirthday().trim().equals("")) {
            teacher.setBirthday(cur.get().getBirthday());
        }
        if (teacher.getPhone() == null || teacher.getPhone().trim().equals("")) {
            teacher.setPhone(cur.get().getPhone());
        }
        if (teacher.getClasses() == null) {
            teacher.setClasses(cur.get().getClasses());
        }
        return teacher;
    }

    public List<Teacher> getAllTeacher(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Teacher> pagedResult = teacherRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

}
