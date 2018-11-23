package com.project.service;

import com.project.dao.GradeRepository;
import com.project.dao.StudentRepository;
import com.project.model.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Student;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Grade create(Grade grade, Integer studentId) {
        return createNew(grade, studentId);
    }

    private Grade createNew(final Grade grade, Integer studentId) {
        final Student student = studentRepository.findById(studentId);
        final Grade result = new Grade();
        result.setStudent(student);
        result.setSubject(grade.getSubject());
        result.setValue(grade.getValue());
        return gradeRepository.save(result);
    }

    @Override
    public void delete(Grade grade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
