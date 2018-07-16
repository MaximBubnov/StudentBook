package com.max.service;

import com.max.domain.GroupName;
import com.max.domain.Role;
import com.max.domain.Subject;
import com.max.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepo subjectRepo;

    public List<Subject> findAll() {
        return subjectRepo.findAll();
    }

    public boolean addSubjectBol(Subject subject) {

        Subject subjectFromDb = subjectRepo.findByName(subject.getName());

        if(subjectFromDb != null) {
            return false;
        }

        return true;
    }

    public void addSubject(Subject subject, Map<String, String> form) {

        Set<String> groups = Arrays.stream(GroupName.values())
                .map(GroupName::name)
                .collect(Collectors.toSet());

        for (String key : form.keySet()) {
            if(groups.contains(key)) {
                subject.setGroupNames(Collections.singleton(GroupName.valueOf(key)));
            }
        }
        subjectRepo.save(subject);
    }

    public void deleteSubject(Long id) {
        subjectRepo.deleteById(id);
    }

    public void saveSubject(Subject subject, String name, Map<String, String> form, String description) {

        subject.setName(name);

        Set<String> groups = Arrays.stream(GroupName.values())
                .map(GroupName::name)
                .collect(Collectors.toSet());

        subject.getGroupNames().clear();

        for (String key : form.keySet()) {
            if(groups.contains(key)) {
                subject.getGroupNames().add(GroupName.valueOf(key));
            }
        }

        subject.setDescription(description);

        subjectRepo.save(subject);
    }
}
