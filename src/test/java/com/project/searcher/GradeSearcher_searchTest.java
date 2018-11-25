package com.project.searcher;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.project.dao.GradeRepository;
import com.project.model.Grade;
import com.project.model.Student;

@RunWith(MockitoJUnitRunner.class)
public class GradeSearcher_searchTest {

    @InjectMocks
    private final GradeSearcherImpl testedService = new GradeSearcherImpl();

    @Mock
    private Grade grade;

    @Mock
    private Student student;

    @Mock
    private List<Grade> expectedResultList;

    @Mock
    private GradeRepository gradeRepository;

    @After
    public void clean() {
        reset(grade, student, gradeRepository, expectedResultList);
    }

    @Test
    public void findyByStudent_search() {
        when(gradeRepository.findByStudent(student)).thenReturn(expectedResultList);
        final List<Grade> result = testedService.findByStudent(student);
        assertEquals(expectedResultList, result);
        verify(gradeRepository).findByStudent(student);
    }

    @Test
    public void findOne_search() {
        when(gradeRepository.findOne(grade.getId())).thenReturn(grade);
        final Grade result = testedService.findOne(grade.getId());
        assertEquals(grade, result);
        verify(gradeRepository).findOne(grade.getId());
    }
}
