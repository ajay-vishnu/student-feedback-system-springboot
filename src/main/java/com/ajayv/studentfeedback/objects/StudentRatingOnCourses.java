package com.ajayv.studentfeedback.objects;

public class StudentRatingOnCourses {
    private String studentUSN;
    private String courseId;
    private float rating;
    private String review;

    public StudentRatingOnCourses() {
    }

    public StudentRatingOnCourses(String studentUSN, String courseId, float rating, String review) {
        this.studentUSN = studentUSN;
        this.courseId = courseId;
        this.rating = rating;
        this.review = review;
    }

    public String getStudentUSN() {
        return studentUSN;
    }

    public void setStudentUSN(String studentUSN) {
        this.studentUSN = studentUSN;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "StudentRatingOnCourses{" +
                "studentUSN='" + studentUSN + '\'' +
                ", courseId='" + courseId + '\'' +
                ", rating=" + rating +
                ", review='" + review + '\'' +
                '}';
    }
}
