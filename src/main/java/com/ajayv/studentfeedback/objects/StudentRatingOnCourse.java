package com.ajayv.studentfeedback.objects;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "StudentRatingOnCourse")
@Table(
        name = "student_rating_on_course",
        uniqueConstraints = @UniqueConstraint(name = "unique_student_course", columnNames = {"student_usn", "course_id"})
)
public class StudentRatingOnCourse {
    @Id
    @SequenceGenerator(
            name = "student_rating_on_course_sequence",
            sequenceName = "student_rating_on_course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_rating_on_course_sequence"
    )
    @Column(
            name = "student_rating_on_course_id",
            updatable = false
    )
    private Long studentRatingCourseId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            foreignKey = @ForeignKey(name = "student_rating_foreign_key"),
            name = "student_usn",
            referencedColumnName = "usn",
            nullable = false
    )
    private Student student;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            foreignKey = @ForeignKey(name = "course_rating_foreign_key"),
            name = "course_id",
            referencedColumnName = "id",
            nullable = false
    )
    private Course course;
    private Integer rating;
    private String review;

    public StudentRatingOnCourse() {
    }

    public StudentRatingOnCourse(Student student, Course course, Integer rating, String review) {
        this.student = student;
        this.course = course;
        this.rating = rating;
        this.review = review;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
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
                "student=" + student +
                ", course=" + course +
                ", rating=" + rating +
                ", review='" + review + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentRatingOnCourse)) return false;
        StudentRatingOnCourse that = (StudentRatingOnCourse) o;
        return student.equals(that.student) && course.equals(that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, course);
    }
}
