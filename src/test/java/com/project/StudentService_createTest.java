package com.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

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
import org.mockito.runners.MockitoJUnitRunner;

import com.project.dao.StudentRepository;
import com.project.model.Student;
import com.project.service.StudentServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class StudentService_createTest {

    @InjectMocks
    private final StudentServiceImpl testedService = new StudentServiceImpl();

    @Mock
    private Student student;

    @Mock
    private StudentRepository studentRepository;

    public static final String DATE_PATTERN = "dd/MM/yyyy";

    private final String firstName = RandomStringUtils.randomAlphabetic(6);
    private final String lastName = RandomStringUtils.randomAlphabetic(6);
    private final String className = RandomStringUtils.randomAlphabetic(6);
    private final String streetAdress = RandomStringUtils.randomAlphabetic(6);
    private final String houseNumber = RandomStringUtils.randomAlphabetic(6);
    private final String city = RandomStringUtils.randomAlphabetic(6);
    private final String zipCode = RandomStringUtils.randomAlphabetic(6);
    private final String pesel = RandomStringUtils.randomAlphabetic(6);
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
        when(studentRepository.save(student)).thenReturn(student);
    }

    @After
    public void clean() {
        reset(student, studentRepository);
    }

    @Test
    public void correctData_create() {
        final Student result = testedService.create(student);
        assertNotNull(result);
        assertEquals(firstName, result.getFirstName());
        assertEquals(lastName, result.getLastName());
        assertEquals(className, result.getClassName());
        assertEquals(streetAdress, result.getStreetAdress());
        assertEquals(houseNumber, result.getHouseNumber());
        assertEquals(city, result.getCity());
        assertEquals(zipCode, result.getZipCode());
        assertEquals(pesel, result.getPesel());
        assertEquals(dateOfBirth, result.getDateOfBirth());
        assertEquals(dyslexia, result.getDyslexia());
        verify(studentRepository).save(result);
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
