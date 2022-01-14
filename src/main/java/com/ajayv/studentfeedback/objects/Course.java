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

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "department_id", referencedColumnName = "id")
//    private Department department;

    public Course() {
    }

    public Course(String id) {
        Id = id;
    }

    public Course(String id, String name, Long branchId) {
        Id = id;
        this.name = name;
//        this.department = new Department(branchId);
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

//    public Long getDepartment() {
//        return department.getId();
//    }
//
//    public void setDepartment(Long branchId) {
//        this.department = new Department(branchId);
//    }

    @Override
    public String toString() {
        return "Courses{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", branch='" +/* department +*/ '\'' +
                '}';
    }
}
