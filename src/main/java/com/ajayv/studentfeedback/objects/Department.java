package com.ajayv.studentfeedback.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Department")
@Table(
        name = "department",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_department_id", columnNames = "department_id")
        }
)
public class Department implements Serializable {
    @Id
    @Column(
            name = "department_id",
            nullable = false
    )
    private String departmentId;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @JsonIgnore
    @OneToMany(
            mappedBy = "department",
            fetch = FetchType.LAZY
    )
    private Set<Lecturer> lecturers = new HashSet<>();
    @JsonIgnore
    @OneToMany(
            mappedBy = "department",
            fetch = FetchType.LAZY
    )
    private List<Course> courses = new ArrayList<>();

    public Department() {
    }

    public Department(String departmentId, String name) {
        this.departmentId = departmentId;
        this.name = name;
    }

    public Department(Long id, String departmentId, String name) {
        this.departmentId = departmentId;
        this.name = name;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Department{" +
                ", departmentId='" + departmentId + '\'' +
                ", name='" + name + '\'' +
                ", courses=" + courses +
                '}';
    }

}
