package com.project.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.project.model.Grade;
import org.apache.commons.lang3.StringUtils;

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

    }

    private void required(Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "value", "NotEmpty");
    }

}
