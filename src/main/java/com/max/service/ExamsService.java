package com.max.service;

import com.max.domain.Exams;
import com.max.domain.Subject;
import com.max.domain.User;
import com.max.repository.ExamsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExamsService {

    @Autowired
    private ExamsRepo examsRepo;

    public List<Exams> findAll() {
        return examsRepo.findAll();
    }

    public void addExams(Exams exams, User user) {
        exams.setUser(user);

        examsRepo.save(exams);
    }

    public void deleteExam(Long id) {
        examsRepo.deleteById(id);
    }


    public void saveExam(Exams exams, Subject subject, Date dateTime) {

        exams.setSubject(subject);
        exams.setDate(dateTime);

        examsRepo.save(exams);
    }
}
