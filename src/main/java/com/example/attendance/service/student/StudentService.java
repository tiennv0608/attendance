package com.example.attendance.service.student;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.attendance.model.Student;
import com.example.attendance.repository.StudentRepository;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student update(Optional<Student> cur, Student student) {
        student.setId(cur.get().getId());
        if (student.getName() == null || student.getName().trim().equals("")) {
            student.setName(cur.get().getName());
        }
        if (student.getAddress() == null || student.getAddress().trim().equals("")) {
            student.setAddress(cur.get().getAddress());
        }
        if (student.getNickname() == null || student.getNickname().trim().equals("")) {
            student.setNickname(cur.get().getNickname());
        }
        if (student.getTelephoneDad() == null || student.getTelephoneDad().trim().equals("")) {
            student.setTelephoneDad(cur.get().getTelephoneDad());
        }
        if (student.getTelephoneMom() == null || student.getTelephoneMom().trim().equals("")) {
            student.setTelephoneMom(cur.get().getTelephoneMom());
        }
        if (student.getBirthday() == null) {
            student.setBirthday(cur.get().getBirthday());
        }
        if (student.getClasses() == null) {
            student.setClasses(cur.get().getClasses());
        }
        return student;
    }

}