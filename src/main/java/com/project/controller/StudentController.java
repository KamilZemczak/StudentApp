package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

import com.project.model.Student;
import com.project.searcher.StudentSearcher;
import com.project.service.StudentService;
import com.project.validator.StudentValidator;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentSearcher studentSearcher;

    @Autowired
    StudentValidator studentValidator;

    @RequestMapping(value = "/add-student", method = RequestMethod.GET)
    public String add(Model model, HttpServletRequest request) {
        model.addAttribute("student", new Student());
        request.setAttribute("mode", "ADD_STUDENT");
        return "index";
    }

    @RequestMapping(value = "/add-student", method = RequestMethod.POST)
    public String add(@ModelAttribute("student") Student student, BindingResult bindingResult, HttpServletRequest request) {
        studentValidator.validate(student, bindingResult);
        if (bindingResult.hasErrors()) {
            request.setAttribute("mode", "ADD_STUDENT");
            return "index";
        }
        studentService.create(student);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete-student", method = RequestMethod.GET)
    public String delete(@RequestParam int id, HttpServletRequest request) {
        Student student = studentSearcher.findOne(id);
        studentService.delete(student);
        request.setAttribute("student", studentSearcher.findAll());
        request.setAttribute("mode", "MODE_HOME");
        return "index";
    }
}
