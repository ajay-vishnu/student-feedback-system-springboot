package com.ajayv.studentfeedback.objects;

import com.ajayv.studentfeedback.attribute.DefaultColumns;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Course")
@Table(name = "course")
public class Course extends DefaultColumns {
    private String courseId;
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

    public Course(String courseId) {
        this.courseId = courseId;
    }

    public Course(String courseId, String name, String createdBy) {
        this.courseId = courseId;
        this.name = name;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.updatedBy = createdBy;
        this.updatedAt = LocalDateTime.now();
    }

    public Course(String courseId, String name, Lecturer lecturer, Department department, String createdBy) {
        this.courseId = courseId;
        this.name = name;
        this.lecturer = lecturer;
        this.department = department;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.updatedBy = createdBy;
        this.updatedAt = LocalDateTime.now();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
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
                "Id='" + courseId + '\'' +
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
        return getCourseId().equals(course.getCourseId()) && Objects.equals(getName(), course.getName()) && Objects.equals(getLecturer(), course.getLecturer()) && Objects.equals(getDepartment(), course.getDepartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCourseId(), getName(), getLecturer(), getDepartment());
    }
}
