package com.project.searcher;

import com.project.dao.GradeRepository;
import com.project.model.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.project.model.Student;

@Service
public class GradeSearcherImpl implements GradeSearcher {

    @Autowired
    GradeRepository gradeRepository;

    @Override
    public List<Grade> findByStudent(Student student) {
        return gradeRepository.findByStudent(student);
    }

    @Override
    public Grade findOne(Integer id) {
        return gradeRepository.findOne(id);
    }
}
