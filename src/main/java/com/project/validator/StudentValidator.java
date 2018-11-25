package com.project.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.project.model.Student;
import org.apache.commons.lang3.StringUtils;

@Component
public class StudentValidator implements Validator {

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
        if (!student.getFirstName().matches("[A-Z][a-zA-Z]*")) {
            errors.rejectValue("firstName", "Student.firstName.format");
        }

        if (student.getLastName().length() < 3 || student.getLastName().length() > 32) {
            errors.rejectValue("lastName", "Student.lastName.size");
        }
        if (!student.getLastName().matches("[a-zA-z]+([ '-][a-zA-Z]+)*")) {
            errors.rejectValue("lastName", "Student.lastName.format");
        }

        if (student.getClassName().length() < 2 || student.getClassName().length() > 32) {
            errors.rejectValue("className", "Student.className.size");
        }

        if (student.getStreetAdress().length() < 2 || student.getStreetAdress().length() > 32) {
            errors.rejectValue("streetAdress", "Student.streetAdress.size");
        }

        if (student.getCity().length() < 2 || student.getCity().length() > 32) {
            errors.rejectValue("city", "Student.city.Size");
        }

        if (!student.getZipCode().matches("\\d{2}-\\d{3}")) {
            errors.rejectValue("zipCode", "Student.zipCode.Format");
        }

        if (!isValidPESEL(student.getPesel())) {
            errors.rejectValue("pesel", "Student.pesel.Format");
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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth", "NotEmpty");
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
}
