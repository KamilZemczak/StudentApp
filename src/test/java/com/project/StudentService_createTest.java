package com.project;

import com.project.dao.StudentRepository;
import com.project.model.Student;
import com.project.service.StudentServiceImpl;
import com.project.service.StudentServiceImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.reset;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StudentService_createTest {

    @InjectMocks
    private final StudentServiceImpl testedService = new StudentServiceImpl();

    @Mock
    private Student student;

    @Mock
    private StudentRepository studentRepository;

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
        assertNotNull(result);
        //verify(studentRepository).save(student);
    }

    private Date stringToDate(String date) {
        Date result = null;
        try {
            result = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(StudentService_createTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
