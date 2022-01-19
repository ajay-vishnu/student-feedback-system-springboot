package com.ajayv.studentfeedback.services;

import com.ajayv.studentfeedback.json.StudentRatingOnCourseJson;
import com.ajayv.studentfeedback.objects.Course;
import com.ajayv.studentfeedback.objects.Student;
import com.ajayv.studentfeedback.objects.StudentRatingOnCourse;
import com.ajayv.studentfeedback.repositories.StudentRatingOnCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentRatingOnCourseService {
    @Autowired
    StudentRatingOnCourseRepository studentRatingOnCourseRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

    public List<StudentRatingOnCourse> getStudentRatingOnCourses()  {
        return studentRatingOnCourseRepository.findAll();
    }

    public Optional<StudentRatingOnCourse> getStudentRatingOnCourseById(String usn, String courseId)    {
        Student student = studentService.getStudentByUsn(usn).orElseThrow(() -> new IllegalStateException("Student with USN " + usn + " does not exist."));
        Course course = courseService.getCourseById(courseId).orElseThrow(() -> new IllegalStateException(courseId + " course does not exist."));
        return studentRatingOnCourseRepository.findByStudentAndCourse(student, course);
    }

    public void addNewStudentRatingOnCourse(StudentRatingOnCourseJson studentRatingOnCourseJson)  {
        if (studentRatingOnCourseJson.getCreatedBy() != null && studentRatingOnCourseJson.getCreatedBy().length() > 0) {
            Student student = studentService.getStudentByUsn(studentRatingOnCourseJson.getUsn()).orElseThrow(() -> new IllegalStateException("Student with USN " + studentRatingOnCourseJson.getUsn() + " does not exist."));
            Course course = courseService.getCourseById(studentRatingOnCourseJson.getCourseId()).orElseThrow(() -> new IllegalStateException(studentRatingOnCourseJson.getCourseId() + " course does not exist."));
            Optional<StudentRatingOnCourse> studentRatingOnCourseOptional = studentRatingOnCourseRepository.findByStudentAndCourse(student, course);
            if (studentRatingOnCourseOptional.isPresent()) {
                throw new IllegalStateException("Student " + studentRatingOnCourseJson.getUsn() + " has already rated the course " + studentRatingOnCourseJson.getCourseId());
            }
            StudentRatingOnCourse studentRatingOnCourse = new StudentRatingOnCourse(student, course, Integer.parseInt(studentRatingOnCourseJson.getRating()), studentRatingOnCourseJson.getReview(), studentRatingOnCourseJson.getCreatedBy());
            studentRatingOnCourseRepository.save(studentRatingOnCourse);
        }
        else    {
            throw new IllegalStateException("Must mention the createdBy parameter to update the database.");
        }
    }

    @Transactional
    public void deleteStudentRatingOnCourse(String usn, String courseId, String updatedBy)    {
        if (updatedBy != null && updatedBy.length() > 0) {
            StudentRatingOnCourse studentRatingOnCourse = getStudentCourse(usn, courseId);
            studentRatingOnCourse.setDeleted(true);
            studentRatingOnCourse.setUpdatedBy(updatedBy);
            studentRatingOnCourse.setUpdatedAt(LocalDateTime.now());
        }
        else    {
            throw new IllegalStateException("Must mention the updatedBy parameter to update the database.");
        }
    }

    @Transactional
    public void updateStudentRatingOnCourse(String usn, String courseId, String rating, String review, String updatedBy)  {
        if (updatedBy != null && updatedBy.length() > 0) {
            Boolean flag = false;
            StudentRatingOnCourse studentRatingOnCourse = getStudentCourse(usn, courseId);
            if (review != null && review.length() > 0 && !Objects.equals(studentRatingOnCourse.getReview(), review))    {
                studentRatingOnCourse.setReview(review);
                flag = true;
            }
            if (rating != null && rating.length() > 0 && !Objects.equals(studentRatingOnCourse.getRating(), Integer.parseInt(rating)))  {
                studentRatingOnCourse.setRating(Integer.parseInt(rating));
                flag = true;
            }
            if (flag) {
                studentRatingOnCourse.setUpdatedBy(updatedBy);
                studentRatingOnCourse.setUpdatedAt(LocalDateTime.now());
            }
        }
        else    {
            throw new IllegalStateException("Must mention the updatedBy parameter to update the database.");
        }
    }

    private StudentRatingOnCourse getStudentCourse(String usn, String courseId) {
        Student student = studentService.getStudentByUsn(usn).orElseThrow(() -> new IllegalStateException("Student with USN " + usn + " does not exist."));
        Course course = courseService.getCourseById(courseId).orElseThrow(() -> new IllegalStateException(courseId + " course does not exist."));
        return studentRatingOnCourseRepository.findByStudentAndCourse(student, course).orElseThrow(() -> new IllegalStateException("The student " + usn + " hasn't rated the course " + courseId + " yet."));
    }
}
