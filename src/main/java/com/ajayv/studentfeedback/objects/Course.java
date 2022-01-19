package com.ajayv.studentfeedback.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity(name = "Courses")
@Table(name = "course")
public class Course implements Serializable {
    @Id
    private String Id;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            foreignKey = @ForeignKey(name = "lecturer_course_foreign_key"),
            name = "lecturer_id",
            referencedColumnName = "lecturer_id"
    )
    private Lecturer lecturer;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            foreignKey = @ForeignKey(name = "department_course_foreign_key"),
            name = "department_id",
            referencedColumnName = "department_id"
    )
    private Department department;
    @JsonIgnore
    @OneToMany(
            mappedBy = "course",
            fetch = FetchType.LAZY
    )
    private List<StudentRatingOnCourse> studentRatingOnCourses = new ArrayList<>();

    public Course() {
    }

    public Course(String id) {
        Id = id;
    }

    public Course(String id, String name) {
        Id = id;
        this.name = name;
    }

    public Course(String id, String name, Lecturer lecturer, Department department) {
        Id = id;
        this.name = name;
        this.lecturer = lecturer;
        this.department = department;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<StudentRatingOnCourse> getStudentRatingOnCourses() {
        return studentRatingOnCourses;
    }

    @Override
    public String toString() {
        return "Course{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", lecturer=" + lecturer +
                ", department=" + department +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return getId().equals(course.getId()) && Objects.equals(getName(), course.getName()) && Objects.equals(getLecturer(), course.getLecturer()) && Objects.equals(getDepartment(), course.getDepartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLecturer(), getDepartment());
    }
}
