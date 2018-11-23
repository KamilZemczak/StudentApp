package com.project.service;

import com.project.model.Student;

public interface StudentService {

    Student create(Student student);

    Student update(Student student);

    void delete(Student student);
}
