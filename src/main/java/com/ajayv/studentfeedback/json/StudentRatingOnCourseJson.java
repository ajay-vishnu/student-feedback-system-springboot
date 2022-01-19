package com.ajayv.studentfeedback.json;

public class StudentRatingOnCourseJson {
    private String usn;
    private String courseId;
    private String rating;
    private String review;
    private String createdBy;
    private String updatedBy;

    public StudentRatingOnCourseJson(String usn, String courseId, String rating, String review) {
        this.usn = usn;
        this.courseId = courseId;
        this.rating = rating;
        this.review = review;
    }

    public String getUsn() {
        return usn;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }
}
