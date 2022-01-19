package com.ajayv.studentfeedback.objects;

import com.ajayv.studentfeedback.attribute.DefaultColumns;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Student")
@Table(
        name = "student",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_usn_unique", columnNames = "usn"),
                @UniqueConstraint(name = "student_phone_unique", columnNames = "phone")
        }

)
public class Student extends DefaultColumns {
    @Column(
            name = "usn",
            updatable = false,
            nullable = false
    )
    private String usn;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(
            name = "phone",
            nullable = false
    )
    private String phone;
    @Column(
            name = "dob",
            nullable = false
    )
    private LocalDate dob;
    @Column(
            name = "date_of_joining"
    )
    private LocalDate dateOfJoining;
    @Column(
            name = "location"
    )
    private String location;
    @Transient
    private Integer age;
    @Transient
    private String email;
    @JsonIgnore
    @OneToMany(
            mappedBy = "student",
            fetch = FetchType.LAZY
    )
    private List<StudentRatingOnCourse> studentRatingOnCourses = new ArrayList<>();

    public Student() {
    }

    public Student(String usn,
                   String name,
                   String phone,
                   LocalDate dob,
                   LocalDate dateOfJoining,
                   String location,
                   String createdBy) {
        this.usn = usn;
        this.name = name;
        this.phone = phone;
        this.dob = dob;
        this.dateOfJoining = dateOfJoining;
        this.location = location;
        this.createdAt = LocalDateTime.now();
        this.createdBy = createdBy;
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = createdBy;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return usn + "@nieit.ac.in";
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<StudentRatingOnCourse> getStudentRatingOnCourses() {
        return studentRatingOnCourses;
    }

    @Override
    public String toString() {
        return "Student{" +
                ", usn='" + usn + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", dob=" + dob +
                ", dateOfJoining=" + dateOfJoining +
                ", location='" + location + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getUsn().equals(student.getUsn()) && Objects.equals(getName(), student.getName()) && Objects.equals(getPhone(), student.getPhone()) && Objects.equals(getDob(), student.getDob()) && Objects.equals(getDateOfJoining(), student.getDateOfJoining()) && Objects.equals(getLocation(), student.getLocation()) && Objects.equals(getAge(), student.getAge()) && Objects.equals(getEmail(), student.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhone(), getDob(), getDateOfJoining(), getLocation(), getAge(), getEmail());
    }
}
