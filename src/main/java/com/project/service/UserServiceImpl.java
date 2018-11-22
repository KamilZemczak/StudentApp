package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import com.project.model.User;
import com.project.dao.UserRepository;
import com.project.model.Stock;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getCurrentUser() {
        User user = userRepository.findByUsername(getUserName());
        if (user == null) {
            throw new SecurityException();
        }
        return user;
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void updatePassword(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }

    @Override
    public void updateMoney(User user, Stock stock, Integer amount) {
        user.setMoney(user.getMoney().subtract(stock.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(amount))));
        update(user);
    }

    @Override
    public void addMoney(Stock stock, Integer amountToSell, User user) {
        BigDecimal moneyToAdd = stock.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(amountToSell));
        user.setMoney(user.getMoney().add(moneyToAdd));
    }

    @Override
    public void updateSurnameAndMoney(User user, User userForm) {
        user.setSurname(userForm.getSurname());
        user.setMoney(userForm.getMoney());
        update(user);
    }

    @Override
    public void updateAllValues(User user, User userForm) {
        user.setUsername(userForm.getUsername());
        user.setSurname(userForm.getSurname());
        user.setMoney(userForm.getMoney());
        if (!userForm.getPassword().isEmpty()) {
            user.setPassword(userForm.getPassword());
            user.setPasswordConfirm(userForm.getPasswordConfirm());
            updatePassword(user);
        }
        update(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public void updateProfile(User user, User userForm) {
        if (!user.getUsername().equals(userForm.getUsername()) || !userForm.getPassword().isEmpty()) {
            updateAllValues(user, userForm);
        } else {
            updateSurnameAndMoney(user, userForm);
        }
    }

    private String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            return auth.getName();
        }
        return "";
    }
}
