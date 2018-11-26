package com.project.validator;

import com.project.ValidatorTest;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.reset;
import static org.mockito.Matchers.anyString;

import org.springframework.validation.Errors;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.project.dao.StudentRepository;
import com.project.model.Student;

@RunWith(MockitoJUnitRunner.class)
public class StudentValidator_validateTest {

    @InjectMocks
    private final StudentValidator testedValidator = spy(StudentValidator.class);

    @Mock
    private Student student;

    @Mock
    private Errors errors;

    @Mock
    private StudentRepository studentRepository;

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

    @Before
    public void setUp() {
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
    }

    @After
    public void clean() {
        reset(testedValidator, errors);
    }

    @Test
    public void corretData_validate() {
        when(errors.getFieldValue(anyString())).thenReturn(mock(Object.class));
        testedValidator.validate(student, errors);
        validatorTest.validateNeverRejectValue(errors);
    }

    @Test
    public void emptyData_validate() {
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
        validatorTest.validateRejectIfEmptyOrWhitespace(errors, "dyslexia", "NotEmpty");
    }

    @Test
    public void invalidData_validate() {
        when(student.getFirstName()).thenReturn("jan");
        when(student.getLastName()).thenReturn("kowalski");
        when(student.getStreetAdress()).thenReturn("a1");
        when(student.getCity()).thenReturn("21");
        when(student.getZipCode()).thenReturn("84-8000");
        when(student.getPesel()).thenReturn("242444");
        testedValidator.validate(student, errors);
        validatorTest.validateRejectValue(errors, "firstName", "Student.firstName.format");
        validatorTest.validateRejectValue(errors, "lastName", "Student.lastName.format");
        validatorTest.validateRejectValue(errors, "streetAdress", "Student.streetAdress.format");
        validatorTest.validateRejectValue(errors, "city", "Student.city.format");
        validatorTest.validateRejectValue(errors, "pesel", "Student.pesel.format");
        validatorTest.validateRejectValue(errors, "zipCode", "Student.zipCode.format");
    }

    @Test
    public void wrongSize_validate() {
        when(student.getFirstName()).thenReturn("Az");
        when(student.getLastName()).thenReturn("Zz");
        when(student.getClassName()).thenReturn("13223");
        when(student.getStreetAdress()).thenReturn("A");
        when(student.getCity()).thenReturn("B");
        testedValidator.validate(student, errors);
        validatorTest.validateRejectValue(errors, "firstName", "Student.firstName.size");
        validatorTest.validateRejectValue(errors, "lastName", "Student.lastName.size");
        validatorTest.validateRejectValue(errors, "className", "Student.className.size");
        validatorTest.validateRejectValue(errors, "streetAdress", "Student.streetAdress.size");
        validatorTest.validateRejectValue(errors, "city", "Student.city.size");
    }

    @Test
    public void nonUniquePesel_validate() {
        doReturn(student).when(studentRepository).findByPesel(pesel);
        testedValidator.validate(student, errors);
        validatorTest.validateRejectValue(errors, "pesel", "Student.pesel.duplicate");
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
