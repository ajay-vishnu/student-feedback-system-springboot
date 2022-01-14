package com.ajayv.studentfeedback.objects;

import javax.persistence.*;

@Entity(name = "Courses")
@Table(name = "course")
public class Course {
    @Id
    private String Id;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @ManyToOne()
    private Lecturer lecturer;

    public Course() {
    }

    public Course(String id) {
        Id = id;
    }

    public Course(String id, String name) {
        Id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Courses{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", branch='" + '\'' +
                '}';
    }
}
