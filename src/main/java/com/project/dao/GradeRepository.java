package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.project.model.Grade;
import com.project.model.Student;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {

    List<Grade> findByStudent(Student student);
}
