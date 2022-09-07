package com.example.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.attendance.model.Classes;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {

}
