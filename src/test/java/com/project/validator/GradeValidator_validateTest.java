package com.project.validator;

import static org.mockito.Mockito.reset;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.springframework.validation.Errors;

import java.math.BigDecimal;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.project.model.Grade;
import com.project.ValidatorTest;

@RunWith(MockitoJUnitRunner.class)
public class GradeValidator_validateTest {

    @InjectMocks
    private final GradeValidator testedValidator = spy(GradeValidator.class);

    @Spy
    private Grade grade;

    @Mock
    private Errors errors;

    private final ValidatorTest validatorTest = new ValidatorTest();

    private final String subject = RandomStringUtils.randomAlphabetic(6);

    @After
    public void clean() {
        reset(testedValidator, errors);
    }

    @Test
    public void correctData_validate() {
        when(grade.getValue()).thenReturn(new BigDecimal(6.0));
        when(grade.getSubject()).thenReturn(subject);
        when(errors.getFieldValue(anyString())).thenReturn(mock(Object.class));
        testedValidator.validate(grade, errors);
        validatorTest.validateNeverRejectValue(errors);
    }

    @Test
    public void wrongGradeValue_validate() {
        when(grade.getValue()).thenReturn(new BigDecimal(7));
        when(grade.getSubject()).thenReturn(subject);
        testedValidator.validate(grade, errors);
        validatorTest.validateRejectValue(errors, "value", "Grade.value.format");
    }

    @Test
    public void wrongSubjectValue_validate() {
        when(grade.getValue()).thenReturn(new BigDecimal(5));
        when(grade.getSubject()).thenReturn("2435352");
        testedValidator.validate(grade, errors);
        validatorTest.validateRejectValue(errors, "subject", "Grade.subject.format");
    }

    @Test
    public void wrongGradeValueAndEmptySubject_validate() {
        when(grade.getValue()).thenReturn(new BigDecimal(3.7));
        when(grade.getSubject()).thenReturn("");
        testedValidator.validate(grade, errors);
        validatorTest.validateRejectValue(errors, "value", "Grade.value.format");
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "subject", "NotEmpty");
    }

    @Test
    public void emptyGradeValueAndSubject_validate() {
        when(grade.getValue()).thenReturn(new BigDecimal(0));
        when(grade.getSubject()).thenReturn("");
        testedValidator.validate(grade, errors);
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "value", "NotEmpty");
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "subject", "NotEmpty");
    }
}
