package com.ajayv.studentfeedback.objects;

public class LecturerPositionDetails {
    private String lecturerId;
    private String departmentId;
    private String position;

    public LecturerPositionDetails() {
    }

    public LecturerPositionDetails(String lecturerId, String departmentId, String position) {
        this.lecturerId = lecturerId;
        this.departmentId = departmentId;
        this.position = position;
    }

    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "LecturerPositionDetails{" +
                "lecturerId='" + lecturerId + '\'' +
                ", teacherId='" + departmentId + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
