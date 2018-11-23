package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {

}
