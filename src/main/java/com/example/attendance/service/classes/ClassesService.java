package com.example.attendance.service.classes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.attendance.model.Classes;
import com.example.attendance.repository.ClassesRepository;

@Service
public class ClassesService implements IClassesService {

    @Autowired
    private ClassesRepository classesRepository;

    @Override
    public Iterable<Classes> findAll() {
        return classesRepository.findAll();
    }

    @Override
    public Optional<Classes> findById(Long id) {
        return classesRepository.findById(id);
    }

    @Override
    public Classes save(Classes classes) {
        return classesRepository.save(classes);
    }

    @Override
    public void delete(Long id) {
        classesRepository.deleteById(id);
    }

    @Override
    public Classes update(Optional<Classes> cur, Classes classes) {
        classes.setId(cur.get().getId());
        if (classes.getName() == null || classes.getName().trim().equals("")) {
            classes.setName(cur.get().getName());
        }
        return classes;
    }

}
