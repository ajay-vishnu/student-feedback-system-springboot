package com.ajayv.studentfeedback.json;

import java.time.LocalDate;

public class StudentJson {
    private String usn;
    private String name;
    private String phone;
    private LocalDate dob;
    private LocalDate dateOfJoining;
    private String location;
    private String email;
    private String createdBy;

    public String getUsn() {
        return usn;
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

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getCreatedBy() {
        return createdBy;
    }
}
