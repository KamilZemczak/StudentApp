package com.project.searcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.project.dao.StudentRepository;
import com.project.model.Student;


@Service
public class StudentSearcherImpl implements StudentSearcher {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findOne(Integer id) {
        return studentRepository.findOne(id);
    }

}
