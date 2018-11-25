package com.project.searcher;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import com.project.dao.StudentRepository;
import com.project.model.Student;

@RunWith(MockitoJUnitRunner.class)
public class StudentSearcher_search {

    @InjectMocks
    private final StudentSearcherImpl testedService = new StudentSearcherImpl();

    @Mock
    private Student student;

    @Mock
    private List<Student> expectedResultList;

    @Mock
    private StudentRepository studentRepository;

    @After
    public void clean() {
        reset(student, studentRepository, expectedResultList);
    }

    @Test
    public void findAll_search() {
        when(studentRepository.findAll()).thenReturn(expectedResultList);
        final List<Student> result = testedService.findAll();
        assertEquals(expectedResultList, result);
        verify(studentRepository).findAll();
    }

    @Test
    public void findOne_search() {
        when(studentRepository.findOne(student.getId())).thenReturn(student);
        final Student result = testedService.findOne(student.getId());
        assertEquals(student, result);
        verify(studentRepository).findOne(student.getId());
    }
}
