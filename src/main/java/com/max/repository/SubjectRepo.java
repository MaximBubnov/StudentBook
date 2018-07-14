package com.max.repository;

import com.max.domain.GroupName;
import com.max.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepo extends JpaRepository<Subject, Long> {

    Subject findByName(String name);

    List<Subject> findByGroupNames(GroupName groupName);
}
