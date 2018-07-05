package com.max.repository;

import com.max.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepo extends JpaRepository<Subject, Long> {

    Subject findByName(String name);
}
