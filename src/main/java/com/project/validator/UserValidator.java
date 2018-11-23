package com.project.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

import com.project.model.Student;
import com.project.searcher.StudentSearcher;

@Component
public class UserValidator implements Validator {

    @Autowired
    private StudentSearcher userSearcher;

    @Override
    public boolean supports(Class<?> aClass) {
        return Student.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Student student = (Student) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "notEmpty");
        if (student.getFirstName().length() < 3 || student.getFirstName().length() > 32) {
            errors.rejectValue("firstName", "size.student.firstName");
        }
        if (student.getFirstName().matches("[A-Z][a-zA-Z]*")) {
            errors.rejectValue("firstName", "form.student.firstName");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "notEmpty");
        if (student.getLastName().length() < 3 || student.getLastName().length() > 32) {
            errors.rejectValue("lastName", "Length.userForm.name"); //todo
        }
        if (student.getLastName().matches("[a-zA-z]+([ '-][a-zA-Z]+)*")) {
            errors.rejectValue("lastName", "Letter.userForm.name");
        }

//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "NotEmpty");
//        if (user.getSurname().length() < 3 || user.getName().length() > 26) {
//            errors.rejectValue("surname", "Length.userForm.surname");
//        }
//        if (!user.getSurname().isEmpty() && !Character.isUpperCase(user.getSurname().charAt(0))) {
//            errors.rejectValue("surname", "Letter.userForm.surname");
//        }
//
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
//        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
//            errors.rejectValue("password", "Size.userForm.password");
//        }
//
//        if (user.getPasswordConfirm() != null && !user.getPasswordConfirm().equals(user.getPassword())) {
//            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
//        }
//
//        if (user.getMoney() != null && user.getMoney().compareTo(BigDecimal.ZERO) < 0) {
//            errors.rejectValue("money", "Negative.userForm.money");
//        }
    }
}
