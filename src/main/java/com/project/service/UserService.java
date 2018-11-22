package com.project.service;

import com.project.model.Stock;
import com.project.model.User;

public interface UserService {

    User getCurrentUser();
    
    void save(User user);

    void update(User User);

    void updatePassword(User user);

    void updateMoney(User user, Stock stock, Integer amount);

    void addMoney(Stock stock, Integer amountToSell, User user);

    void updateSurnameAndMoney(User user, User userForm);

    void updateAllValues(User user, User userForm);
    
    void updateProfile(User user, User userForm);
}
