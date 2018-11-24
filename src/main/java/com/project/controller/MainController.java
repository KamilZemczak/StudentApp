package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

import com.project.searcher.StudentSearcher;

@Controller
public class MainController {

    @Autowired
    StudentSearcher studentSearcher;

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        request.setAttribute("student", studentSearcher.findAll());
        request.setAttribute("mode", "MODE_HOME");
        return "index";
    }
}
