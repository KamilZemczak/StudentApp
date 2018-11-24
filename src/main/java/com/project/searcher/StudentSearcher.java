package com.project.searcher;

import com.project.model.Student;
import java.util.List;

public interface StudentSearcher {

    List<Student> findAll();

    Student findOne(Integer id);
}
