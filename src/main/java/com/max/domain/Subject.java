package com.max.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "subjects")
public class Subject implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Subject`s name cannot be empty")
    private String name;

    @ElementCollection(targetClass = GroupName.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "subject_group", joinColumns = @JoinColumn(name = "subject_id"))
    @Enumerated(EnumType.STRING)
    private Set<GroupName> groupNames;

    public Set<GroupName> getGroupNames() {
        return groupNames;
    }

    public void setGroupNames(Set<GroupName> groupNames) {
        this.groupNames = groupNames;
    }

    public Subject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return id == subject.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
