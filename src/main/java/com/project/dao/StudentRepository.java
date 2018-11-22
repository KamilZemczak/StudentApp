package com.project.dao;

import com.project.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByUsername(String username);

    Student findById(Integer id);
}
