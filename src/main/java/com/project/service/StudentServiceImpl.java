package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import com.project.model.Student;
import com.project.dao.UserRepository;
import com.project.model.Stock;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Student getCurrentUser() {
        Student user = userRepository.findByUsername(getUserName());
        if (user == null) {
            throw new SecurityException();
        }
        return user;
    }

    @Override
    public void save(Student user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void update(Student user) {
        userRepository.save(user);
    }

    @Override
    public void updatePassword(Student user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }

    @Override
    public void updateMoney(Student user, Stock stock, Integer amount) {
        user.setMoney(user.getMoney().subtract(stock.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(amount))));
        update(user);
    }

    @Override
    public void addMoney(Stock stock, Integer amountToSell, Student user) {
        BigDecimal moneyToAdd = stock.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(amountToSell));
        user.setMoney(user.getMoney().add(moneyToAdd));
    }

    @Override
    public void updateSurnameAndMoney(Student user, Student userForm) {
        user.setSurname(userForm.getSurname());
        user.setMoney(userForm.getMoney());
        update(user);
    }

    @Override
    public void updateAllValues(Student user, Student userForm) {
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
    public void updateProfile(Student user, Student userForm) {
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
