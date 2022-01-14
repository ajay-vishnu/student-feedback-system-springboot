package com.ajayv.studentfeedback.objects;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity(name = "Student")
@Table(
        name = "student",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_usn_unique", columnNames = "usn"),
                @UniqueConstraint(name = "student_phone_unique", columnNames = "phone")
        }

)
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Column(
            name = "user_id",
            updatable = false
    )
    private Long userid;
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

    public Student() {
    }

    public Student(String usn,
                   String name,
                   String phone,
                   LocalDate dob,
                   LocalDate dateOfJoining,
                   String location) {
        this.usn = usn;
        this.name = name;
        this.phone = phone;
        this.dob = dob;
        this.dateOfJoining = dateOfJoining;
        this.location = location;
    }

    public Student(Long userid,
                   String usn,
                   String name,
                   String phone,
                   LocalDate dob,
                   LocalDate dateOfJoining,
                   String location) {
        this.userid = userid;
        this.usn = usn;
        this.name = name;
        this.phone = phone;
        this.dob = dob;
        this.dateOfJoining = dateOfJoining;
        this.location = location;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
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

    @Override
    public String toString() {
        return "Student{" +
                "userid=" + userid +
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
}
