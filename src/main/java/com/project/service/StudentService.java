package com.project.service;

import com.project.model.Stock;
import com.project.model.Student;

public interface StudentService {

    Student getCurrentUser();
    
    void save(Student user);

    void update(Student User);

    void updatePassword(Student user);

    void updateMoney(Student user, Stock stock, Integer amount);

    void addMoney(Stock stock, Integer amountToSell, Student user);

    void updateSurnameAndMoney(Student user, Student userForm);

    void updateAllValues(Student user, Student userForm);
    
    void updateProfile(Student user, Student userForm);
}
