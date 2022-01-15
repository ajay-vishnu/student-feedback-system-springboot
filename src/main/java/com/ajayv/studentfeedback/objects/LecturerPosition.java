package com.ajayv.studentfeedback.objects;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "LecturerPosition")
@Table(name = "lecturer_position")
public class LecturerPosition implements Serializable {
    @Id
    @SequenceGenerator(
            name = "lecturer_position_sequence",
            sequenceName = "lecturer_position_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lecturer_position_sequence"
    )
    private Long lecturerPositionId;
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(
            foreignKey = @ForeignKey(name = "lecturer_position_foreign_key"),
            name = "lecturer_id",
            referencedColumnName = "lecturer_id"
    )
    private Lecturer lecturer;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            foreignKey = @ForeignKey(name = "department_governs_foreign_key"),
            name = "department_id",
            referencedColumnName = "department_id"
    )
    private Department department;
    @Column(name = "position")
    private String position;

    public LecturerPosition() {
    }

    public LecturerPosition(Lecturer lecturer, Department department, String position) {
        this.lecturer = lecturer;
        this.department = department;
        this.position = position;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "LecturerPosition{" +
                "lecturer=" + lecturer +
                ", department=" + department +
                ", position='" + position + '\'' +
                '}';
    }
}
