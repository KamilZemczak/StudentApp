package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


import com.project.model.Student;
import com.project.service.StudentService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class StudentController {
    
    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/add-student", method = RequestMethod.GET)
    public String add(HttpServletRequest request) {
        request.setAttribute("mode", "ADD_STUDENT");
        return "index";
    }
    
    
    @RequestMapping(value = "/add-student", method = RequestMethod.POST)
    public String add(@ModelAttribute Student student, BindingResult bindingResult, HttpServletRequest request) {
        studentService.create(student);
        return "index";
    }
    
    /*
    @RequestMapping(value = "/buy-stock", method = RequestMethod.POST)
    public String buyConfirm(@RequestParam int id, HttpServletRequest request) {
    Stock stock = stockSearcher.findOne(id);
    User user = userService.getCurrentUser();
    stockService.roundPrice(stock);
    if (!uniValidator.validateBuyStockData(request, user, stock)) {
    Integer amount = Integer.valueOf(request.getParameter("amount"));
    walletService.createOrUpdate(id, amount, user, stock);
    stockService.subtractAmount(stock, amount);
    userService.updateMoney(user, stock, amount);
    } else {
    return "index";
    }
    return "redirect:/";
    }*/
}
