package com.project.service;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.project.dao.GradeRepository;
import com.project.model.Grade;

@RunWith(MockitoJUnitRunner.class)
public class GradeService_deleteTest {

    @InjectMocks
    private final GradeServiceImpl testedService = new GradeServiceImpl();

    @Mock
    private Grade grade;

    @Mock
    private GradeRepository gradeRepository;

    @After
    public void clean() {
        reset(grade, gradeRepository);
    }

    @Test
    public void correctData_create() {
        testedService.delete(grade);
        verify(gradeRepository).delete(grade);
    }
}
