package com.project.searcher;

import com.project.model.Grade;
import com.project.model.Student;
import java.util.List;

public interface GradeSearcher {

    List<Grade> findByStudent(Student student);

    Grade findOne(Integer id);
}
