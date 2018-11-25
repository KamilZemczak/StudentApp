package com.project.searcher;

import java.util.List;

import com.project.model.Student;

public interface StudentSearcher {

    List<Student> findAll();

    Student findOne(Integer id);
}
