package com.project.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.project.model.Student;
import com.project.dao.StudentRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.time.DateFormatUtils;

@Component
public class StudentValidator implements Validator {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return Student.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Student student = (Student) o;
        required(errors);

        if (student.getFirstName().length() < 3 || student.getFirstName().length() > 32) {
            errors.rejectValue("firstName", "Student.firstName.size");
        }

        if (!student.getFirstName().matches("[A-Z][\\w]+( [A-Z][\\w]+)?")) {
            errors.rejectValue("firstName", "Student.firstName.format");
        }

        if (student.getLastName().length() < 3 || student.getLastName().length() > 32) {
            errors.rejectValue("lastName", "Student.lastName.size");
        }

        if (!student.getLastName().matches("[A-Z][\\w]+(-[A-Z][\\w]+)?")) {
            errors.rejectValue("lastName", "Student.lastName.format");
        }

        if (student.getClassName().length() < 1 || student.getClassName().length() > 2) {
            errors.rejectValue("className", "Student.className.size");
        }

        if (student.getStreetAdress().length() < 2 || student.getStreetAdress().length() > 32) {
            errors.rejectValue("streetAdress", "Student.streetAdress.size");
        }

        if (!student.getStreetAdress().matches("[A-Z][\\w]+([ -][A-Z][\\w]+)?")) {
            errors.rejectValue("streetAdress", "Student.streetAdress.format");
        }

        if (student.getCity().length() < 2 || student.getCity().length() > 32) {
            errors.rejectValue("city", "Student.city.size");
        }

        if (!student.getCity().matches("[A-Z][\\w]+([ -][A-Z][\\w]+)?")) {
            errors.rejectValue("city", "Student.city.format");
        }

        if (!student.getZipCode().matches("\\d{2}-\\d{3}")) {
            errors.rejectValue("zipCode", "Student.zipCode.format");
        }

        if (!isValidPESEL(student.getPesel())) {
            errors.rejectValue("pesel", "Student.pesel.format");
        }

        if (!duplicate(student.getPesel())) {
            errors.rejectValue("pesel", "Student.pesel.duplicate");
        }
    }

    private void required(Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "className", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "streetAdress", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "houseNumber", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zipCode", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pesel", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dyslexia", "NotEmpty");
    }

    private static boolean isValidPESEL(String pesel) {
        int size = pesel.length();
        if (size != 11) {
            return false;
        }
        int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int j = 0, sum = 0, control = 0;
        int csum = Integer.valueOf(pesel.substring(size - 1));
        for (int i = 0; i < size - 1; i++) {
            char c = pesel.charAt(i);
            j = Integer.valueOf(String.valueOf(c));
            sum += j * weights[i];
        }
        control = 10 - (sum % 10);
        if (control == 10) {
            control = 0;
        }
        return (control == csum);
    }

    private boolean duplicate(String pesel) {
        return (studentRepository.findByPesel(pesel) == null);
    }

}
