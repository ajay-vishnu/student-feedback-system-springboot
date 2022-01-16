package com.ajayv.studentfeedback.composite;

import com.ajayv.studentfeedback.objects.Course;
import com.ajayv.studentfeedback.objects.Student;

import java.io.Serializable;
import java.util.Objects;

public class StudentCourseComposite implements Serializable {
    private Student student;
    private Course course;

    public StudentCourseComposite() {
    }

    public StudentCourseComposite(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "StudentCourseComposite{" +
                "student=" + student +
                ", course=" + course +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentCourseComposite)) return false;
        StudentCourseComposite that = (StudentCourseComposite) o;
        return getStudent().equals(that.getStudent()) && getCourse().equals(that.getCourse());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudent(), getCourse());
    }
}
