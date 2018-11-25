package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.StudentRepository;
import com.project.model.Student;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    public static final String DATE_PATTERN = "dd/MM/yyyy";

    @Override
    public Student create(Student student) {
        student.setDateOfBirth(stringToDate(convertDate(student.getPesel())));
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

    private static Date stringToDate(final String value, final SimpleDateFormat formatter) {
        try {
            return formatter.parse(value);
        } catch (ParseException | NullPointerException e) {
            return null;
        }
    }

    private static Date stringToDate(final String value) {
        final SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
        return stringToDate(value, formatter);
    }

    private static String convertDate(String pesel) {
        return pesel.substring(4, 6) + "/" + pesel.substring(2, 4)
                + ((Integer.parseInt(pesel.substring(0, 2)) > 70) ? "/19" : "/20")
                + pesel.substring(0, 2);
    }
}
