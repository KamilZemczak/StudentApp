package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.StudentRepository;
import com.project.model.Student;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student create(Student student) {
        return createNew(student);
    }

    private Student createNew(final Student student) {
        final Student result = new Student();
        result.setFirstName(student.getFirstName());
        result.setLastName(student.getLastName());
        result.setClassName(student.getClassName());
        result.setStreetAdress(student.getStreetAdress());
        result.setHouseNumber(student.getHouseNumber());
        result.setCity(student.getCity());
        result.setZipCode(student.getZipCode());
        result.setPesel(student.getPesel());
        result.setDateOfBirth(student.getDateOfBirth());
        result.setDyslexia(student.getDyslexia());
        studentRepository.save(result);
        return result;
    }

    @Override
    public void delete(Student student) {
        studentRepository.delete(student);
    }
}
