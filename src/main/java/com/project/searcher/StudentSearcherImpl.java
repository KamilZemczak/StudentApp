package com.project.searcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.UserRepository;
import com.project.model.Student;

@Service
public class StudentSearcherImpl implements StudentSearcher {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Student findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
