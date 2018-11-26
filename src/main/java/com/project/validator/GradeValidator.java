package com.project.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

import com.project.model.Grade;

@Component
public class GradeValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Grade.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Grade grade = (Grade) o;
        required(errors);

        if (!grade.getSubject().matches("^[A-Z].*")) {
            errors.rejectValue("subject", "Grade.subject.format");
        }

        if (grade.getValue() != null && !isCorrectGrade(grade.getValue())) {
            errors.rejectValue("value", "Grade.value.format");
        }
    }

    private void required(Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "value", "NotEmpty");
    }

    private static boolean isCorrectGrade(BigDecimal grade) {
        return grade.compareTo(BigDecimal.ONE) >= 0
                && grade.compareTo(new BigDecimal(6)) <= 0
                && grade.remainder(new BigDecimal(0.5)).compareTo(BigDecimal.ZERO) == 0;
    }
}
