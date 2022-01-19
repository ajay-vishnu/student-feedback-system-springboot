package com.ajayv.studentfeedback.json;

import java.time.LocalDate;

public class LecturerJson {
    private String lecturerId;
    private String name;
    private String phone;
    private LocalDate dob;
    private LocalDate dateOfJoining;
    private String position;
    private String departmentId;
    private String createdBy;

    public String getLecturerId() {
        return lecturerId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getDob() {
        return dob;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public String getCreatedBy() {
        return createdBy;
    }
}
