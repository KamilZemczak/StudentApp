package com.project;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.project.dao.StudentRepository;
import com.project.model.Student;
import com.project.service.StudentServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class StudentService_deleteTest {

    @InjectMocks
    private final StudentServiceImpl testedService = new StudentServiceImpl();

    @Mock
    private Student student;

    @Mock
    private StudentRepository studentRepository;

    @After
    public void clean() {
        reset(student, studentRepository);
    }

    @Test
    public void correctData_create() {
        testedService.delete(student);
        verify(studentRepository).delete(student);
    }
}
