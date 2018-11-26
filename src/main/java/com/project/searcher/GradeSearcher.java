package com.project.searcher;

import java.util.List;

import com.project.model.Grade;
import com.project.model.Student;

public interface GradeSearcher {

    List<Grade> findAll();

    List<Grade> findByStudent(Student student);

    Grade findOne(Integer id);
}
