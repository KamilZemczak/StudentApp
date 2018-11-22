package com.project.searcher;

import com.project.model.Student;

public interface StudentSearcher {

    Student findByUsername(String username);
}
