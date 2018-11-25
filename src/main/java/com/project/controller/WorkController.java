package com.project.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.project.model.Student;
import com.project.searcher.StudentSearcher;
import com.project.service.StudentService;

@RestController
public class WorkController {

    @Autowired
    StudentSearcher studentSearcher;

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public List<Student> getStudents() {
        return studentSearcher.findAll();
    }

    //	http://localhost:8080/addStudent?firstName=Kamil&lastName=Zemczak&className=2A&streetAdress=Kwietniowa&houseNumber=54&city=Zabrze&zipCode=41-811&pesel=95092803873&dyslexia=false
    @RequestMapping(value = "/addStudent", method = RequestMethod.GET)
    public Student addStudent(@ModelAttribute("student") Student student) {
        studentService.create(student);
        return student;
    }
}
