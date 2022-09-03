package com.example.attendance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.attendance.model.Classes;

@Repository
public interface ClassesRepository extends CrudRepository<Classes, Long>{
    
}
