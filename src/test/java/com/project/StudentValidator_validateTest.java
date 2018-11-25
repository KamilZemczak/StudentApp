package com.project;

import com.project.dao.StudentRepository;
import com.project.model.Student;
import com.project.validator.StudentValidator;
import static org.mockito.Mockito.reset;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@RunWith(MockitoJUnitRunner.class)
public class StudentValidator_validateTest {

    @InjectMocks
    private final StudentValidator testedValidator = spy(StudentValidator.class);

    @Mock
    private Student student;

    @Mock
    private StudentRepository studentRepository;

    private Errors errors;

    private final ValidatorTest validatorTest = new ValidatorTest();

    @Before
    public void setUp() {
        errors = spy(new BeanPropertyBindingResult(student, student.getClass().getSimpleName()));

    }

    @After
    public void clean() {
        reset(testedValidator, errors, student);
    }

    @Test
    public void aa_addErrors() {
        testedValidator.validate(student, errors);
        assertEquals(500, errors.getErrorCount());
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "className", "NotEmpty");
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "streetAdress", "NotEmpty");
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "houseNumber", "NotEmpty");
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "city", "NotEmpty");
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "zipCode", "NotEmpty");
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "pesel", "NotEmpty");
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "dateOfBirth", "NotEmpty");
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "dyslexia", "");
    }

}
