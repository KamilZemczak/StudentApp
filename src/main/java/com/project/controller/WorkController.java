package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;

import java.util.List;

import com.project.model.Student;
import com.project.model.Grade;
import com.project.searcher.GradeSearcher;
import com.project.searcher.StudentSearcher;
import com.project.service.StudentService;
import com.project.validator.StudentValidator;

@RestController
public class WorkController {

    @Autowired
    StudentSearcher studentSearcher;

    @Autowired
    GradeSearcher gradeSearcher;

    @Autowired
    StudentService studentService;

    @Autowired
    StudentValidator studentValidator;

    @RequestMapping(value = "/student", method = RequestMethod.GET, produces = "application/json")
    public List<Student> getStudents() {
        return studentSearcher.findAll();
    }

    @RequestMapping(value = "/grade", method = RequestMethod.GET, produces = "application/json")
    public List<Grade> getGrades() {
        return gradeSearcher.findAll();
    }

    //http://localhost:8080/addStudent?firstName=Kamil&lastName=Zemczak&className=2A&streetAdress=Kwietniowa&houseNumber=54&city=Zabrze&zipCode=41-811&pesel=95092803873&dyslexia=false
    @RequestMapping(value = "/addStudent", method = RequestMethod.GET)
    public Student addStudent(@ModelAttribute("student") Student student, BindingResult bindingResult) {
        studentValidator.validate(student, bindingResult);
        if (bindingResult.hasErrors()) {
            return null;
        }
        studentService.create(student);
        return student;
    }
}
