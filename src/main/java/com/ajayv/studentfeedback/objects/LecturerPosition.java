package com.ajayv.studentfeedback.objects;

public class LecturerPosition {
    private String lecturerId;
    private Long departmentId;
    private String position;

    public LecturerPosition() {
    }

    public LecturerPosition(String lecturerId, Long departmentId, String position) {
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
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
        return "LecturerPosition{" +
                "lectuerId='" + lecturerId + '\'' +
                ", departmentId=" + departmentId +
                ", position='" + position + '\'' +
                '}';
    }
}
