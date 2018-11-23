package com.project.searcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Student;

@Service
public class StudentSearcherImpl implements StudentSearcher {

    @Override
    public Student findByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
//
//    @Override
//    public Student findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
}
