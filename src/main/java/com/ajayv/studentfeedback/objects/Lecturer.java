package com.ajayv.studentfeedback.objects;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Lecturer")
@Table(
        name = "lecturer",
        uniqueConstraints = {
                @UniqueConstraint(name = "lecturer_unique_id", columnNames = "lecturer_id"),
                @UniqueConstraint(name = "phone_unique_number", columnNames = "phone")
        }
)
public class Lecturer {
    @Id
    @SequenceGenerator(
            name = "lecturer_sequence",
            sequenceName = "lecturer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lecturer_sequence"
    )
    @Column(
            name = "user_id",
            updatable = false
    )
    private Long userId;
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

    public Lecturer() {
    }

    public Lecturer(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    public Lecturer(String lecturerId, String name, String phone, LocalDate dob, LocalDate dateOfJoining) {
        this.lecturerId = lecturerId;
        this.name = name;
        this.phone = phone;
        this.dob = dob;
        this.dateOfJoining = dateOfJoining;
    }

    public Lecturer(Long userId, String lecturerId, String name, String phone, LocalDate dob, LocalDate dateOfJoining) {
        this.userId = userId;
        this.lecturerId = lecturerId;
        this.name = name;
        this.phone = phone;
        this.dob = dob;
        this.dateOfJoining = dateOfJoining;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "Lecturer{" +
                "userId='" + userId + '\'' +
                ", lecturerId='" + lecturerId + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", dob=" + dob +
                ", dateOfJoining=" + dateOfJoining +
                '}';
    }
}
