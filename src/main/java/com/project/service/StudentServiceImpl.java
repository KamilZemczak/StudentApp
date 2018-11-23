package com.project.service;

import com.project.dao.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
        result.setClass2(student.getClass2());
        result.setStreetAdress(student.getStreetAdress());
        result.setHouseNumber(student.getHouseNumber());
        result.setCity(student.getCity());
        result.setZipCode(student.getZipCode());
        result.setPesel(student.getPesel());
        result.setDateOfBirth(student.getDateOfBirth());
        result.setDyslexia(student.getDyslexia());
        return studentRepository.save(result);
    }

    @Override
    public Student update(Student student) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Student student) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
