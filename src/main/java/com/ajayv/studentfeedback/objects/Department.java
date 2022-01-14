package com.ajayv.studentfeedback.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Department")
@Table(name = "department")
public class Department {
    @Id
    @SequenceGenerator(
            name = "department_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "department_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
//    @JsonIgnore
//    @OneToMany(mappedBy = "department")
//    private Set<Course> courses = HashSet<>();

    public Department() {
    }

    public Department(Long id) {
        this.id = id;
    }

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Department(String name) {
        this.name = name;
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

//    public Set<Course> getCourses() {
//        return courses;
//    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
