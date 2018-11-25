package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

import com.project.model.Grade;
import com.project.model.Student;
import com.project.searcher.GradeSearcher;
import com.project.searcher.StudentSearcher;
import com.project.service.GradeService;
import com.project.validator.GradeValidator;

@Controller
public class GradeController {

    @Autowired
    GradeService gradeService;

    @Autowired
    StudentSearcher studentSearcher;

    @Autowired
    GradeSearcher gradeSearcher;

    @Autowired
    GradeValidator gradeValidator;

    @RequestMapping(value = "/add-grade", method = RequestMethod.GET)
    public String add(@RequestParam int id, Model model, HttpServletRequest request) {
        model.addAttribute("student", studentSearcher.findOne(id));
        model.addAttribute("grade", new Grade());
        request.setAttribute("mode", "ADD_GRADE");
        return "index";
    }

    @RequestMapping(value = "/add-grade", method = RequestMethod.POST)
    public String add(@ModelAttribute("grade") Grade grade, @RequestParam("student_id") Integer id, Model model, HttpServletRequest request, BindingResult bindingResult) {
        Student student = studentSearcher.findOne(id);
        gradeValidator.validate(grade, bindingResult);
        if (bindingResult.hasErrors()) {
            request.setAttribute("mode", "ADD_GRADE");
            model.addAttribute("student", student);
            return "index";
        }
        gradeService.create(grade, id);
        model.addAttribute("grade", gradeSearcher.findByStudent(student));
        model.addAttribute("student", student);
        request.setAttribute("mode", "SHOW_GRADE");
        return "index";
    }

    @RequestMapping(value = "/show-grade", method = RequestMethod.GET)
    public String show(@RequestParam int id, Model model, HttpServletRequest request) {
        Student student = studentSearcher.findOne(id);
        model.addAttribute("grade", gradeSearcher.findByStudent(student));
        model.addAttribute("student", student);
        request.setAttribute("mode", "SHOW_GRADE");
        return "index";
    }

    @RequestMapping(value = "/delete-grade", method = RequestMethod.GET)
    public String delete(@RequestParam int id, HttpServletRequest request) {
        Grade grade = gradeSearcher.findOne(id);
        gradeService.delete(grade);
        return "redirect:/";
    }
}
