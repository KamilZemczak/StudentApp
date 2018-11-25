package com.project;

import com.project.model.Student;
import com.project.validator.StudentValidator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import static org.mockito.Mockito.reset;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Errors;

@RunWith(MockitoJUnitRunner.class)
public class StudentValidator_validateTest {

    @InjectMocks
    private final StudentValidator testedValidator = spy(StudentValidator.class);

    @Mock
    private Errors errors;

    private final ValidatorTest validatorTest = new ValidatorTest();

    public static final String DATE_PATTERN = "dd/MM/yyyy";

    private final String firstName = "Kamil";
    private final String lastName = "Zemczak";
    private final String className = RandomStringUtils.randomAlphabetic(1);
    private final String streetAdress = RandomStringUtils.randomAlphabetic(6);
    private final String houseNumber = RandomStringUtils.randomAlphabetic(6);
    private final String city = RandomStringUtils.randomAlphabetic(6);
    private final String zipCode = "41-811";
    private final String pesel = "92071314764";
    private final Date dateOfBirth = stringToDate("28/09/1995");
    private final Boolean dyslexia = RandomUtils.nextBoolean();

    @After
    public void clean() {
        reset(testedValidator, errors);
    }

    @Test
    public void corretData_addErrors() {
        final Student student = spy(Student.class);
        when(errors.getFieldValue(anyString())).thenReturn(mock(Object.class));
        when(student.getFirstName()).thenReturn(firstName);
        when(student.getLastName()).thenReturn(lastName);
        when(student.getClassName()).thenReturn(className);
        when(student.getStreetAdress()).thenReturn(streetAdress);
        when(student.getHouseNumber()).thenReturn(houseNumber);
        when(student.getCity()).thenReturn(city);
        when(student.getZipCode()).thenReturn(zipCode);
        when(student.getPesel()).thenReturn(pesel);
        when(student.getDateOfBirth()).thenReturn(dateOfBirth);
        when(student.getDyslexia()).thenReturn(dyslexia);
        testedValidator.validate(student, errors);
        validatorTest.validateNeverRejectValue(errors);
    }
    
    @Test
    public void emptyData_validate() {
        final Student student = spy(Student.class);
        when(student.getFirstName()).thenReturn("");
        when(student.getLastName()).thenReturn("");
        when(student.getClassName()).thenReturn("");
        when(student.getStreetAdress()).thenReturn("");
        when(student.getHouseNumber()).thenReturn("");
        when(student.getCity()).thenReturn("");
        when(student.getZipCode()).thenReturn("");
        when(student.getPesel()).thenReturn("");
        when(student.getDateOfBirth()).thenReturn(null);
        when(student.getDyslexia()).thenReturn(null);
        testedValidator.validate(student, errors);
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "className", "NotEmpty");
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "streetAdress", "NotEmpty");
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "houseNumber", "NotEmpty");
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "city", "NotEmpty");
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "zipCode", "NotEmpty");
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "pesel", "NotEmpty");
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "dateOfBirth", "NotEmpty");
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "dyslexia", "NotEmpty");
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

}
