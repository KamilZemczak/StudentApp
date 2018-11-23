package com.project.service;

import com.project.model.Grade;

public interface GradeService {

    Grade create(Grade grade, Integer studentId);

    void delete(Grade grade);
}
