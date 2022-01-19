package com.ajayv.studentfeedback.objects;

import com.ajayv.studentfeedback.attribute.DefaultColumns;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Lecturer")
@Table(
        name = "lecturer",
        uniqueConstraints = {
                @UniqueConstraint(name = "lecturer_unique_id", columnNames = "lecturer_id"),
                @UniqueConstraint(name = "phone_unique_number", columnNames = "phone")
        }
)
public class Lecturer extends DefaultColumns {
    @Column(
            name = "lecturer_id",
            updatable = false,
            nullable = false
    )
    private String lecturerId;
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
            name = "date_of_joining",
            nullable = false
    )
    private LocalDate dateOfJoining;
    @Column(
            name = "position"
    )
    private String position;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            foreignKey = @ForeignKey(name = "lecturer_department_foreign_key"),
            name = "department_id",
            referencedColumnName = "department_id"
    )
    private Department department;
    @JsonIgnore
    @OneToMany(
            mappedBy = "lecturer",
            fetch = FetchType.LAZY
    )
    private List<Course> courses = new ArrayList<>();

    public Lecturer() {
    }

    public Lecturer(String lecturerId, String name, String phone, LocalDate dob, LocalDate dateOfJoining, String position, String createdBy) {
        this.lecturerId = lecturerId;
        this.name = name;
        this.phone = phone;
        this.dob = dob;
        this.dateOfJoining = dateOfJoining;
        this.position = position;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.updatedBy = createdBy;
        this.updatedAt = LocalDateTime.now();
    }

    public Lecturer(String lecturerId, String name, String phone, LocalDate dob, LocalDate dateOfJoining, String createdBy) {
        this.lecturerId = lecturerId;
        this.name = name;
        this.phone = phone;
        this.dob = dob;
        this.dateOfJoining = dateOfJoining;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.updatedBy = createdBy;
        this.updatedAt = LocalDateTime.now();
    }

    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                ", lecturerId='" + lecturerId + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", dob=" + dob +
                ", dateOfJoining=" + dateOfJoining +
                ", courses=" + courses +
                '}';
    }
}
