package com.ajayv.studentfeedback.json;

public class StudentRatingOnCourseJson {
    private String usn;
    private String courseId;
    private String rating;
    private String review;

    public StudentRatingOnCourseJson(String usn, String courseId, String rating, String review) {
        this.usn = usn;
        this.courseId = courseId;
        this.rating = rating;
        this.review = review;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
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
        return "StudentRatingOnCourse{" +
                "usn='" + usn + '\'' +
                ", courseId='" + courseId + '\'' +
                ", rating=" + rating +
                ", review='" + review + '\'' +
                '}';
    }
}
