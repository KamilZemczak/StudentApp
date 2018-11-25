package com.project;

import com.project.dao.GradeRepository;
import com.project.dao.StudentRepository;
import com.project.model.Grade;
import com.project.model.Student;
import com.project.service.GradeServiceImpl;
import java.math.BigDecimal;
import static org.mockito.Mockito.reset;

import org.apache.commons.lang3.RandomStringUtils;
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
public class GradeService_createTest {

    @InjectMocks
    private final GradeServiceImpl testedService = new GradeServiceImpl();

    @Mock
    private Student student;
    
    @Mock
    private Grade grade;
    
    @Mock
    private StudentRepository studentRepository;
    
    @Mock
    private GradeRepository gradeRepository;

    private final String subject = RandomStringUtils.randomAlphabetic(6);
    private final BigDecimal value = new BigDecimal(Math.random());

    
    @Before
    public void setUp() {
        when(grade.getSubject()).thenReturn(subject);
        when(grade.getValue()).thenReturn(value);
        when(studentRepository.findOne(student.getId())).thenReturn(student);
        when(gradeRepository.save(grade)).thenReturn(grade);
    }

    @After
    public void clean() {
        reset(grade, student, gradeRepository, studentRepository);
    }

    @Test
    public void correctData_create() {
        final Grade result = testedService.create(grade, student.getId());
        assertEquals(student, result.getStudent());
        assertEquals(subject, result.getSubject());
        assertEquals(value, result.getValue());    
        assertNotNull(result);
        //verify(gradeRepository).save(grade);
    }
}
