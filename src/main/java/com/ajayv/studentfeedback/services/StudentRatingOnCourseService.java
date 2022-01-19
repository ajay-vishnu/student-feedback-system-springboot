package com.ajayv.studentfeedback.services;

import com.ajayv.studentfeedback.objects.Course;
import com.ajayv.studentfeedback.objects.Student;
import com.ajayv.studentfeedback.objects.StudentRatingOnCourse;
import com.ajayv.studentfeedback.repositories.StudentRatingOnCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public void addNewStudentRatingOnCourse(String usn, String courseId, String rating, String review)  {
        Student student = studentService.getStudentByUsn(usn).orElseThrow(() -> new IllegalStateException("Student with USN " + usn + " does not exist."));
        Course course = courseService.getCourseById(courseId).orElseThrow(() -> new IllegalStateException(courseId + " course does not exist."));
        Optional<StudentRatingOnCourse> studentRatingOnCourseOptional = studentRatingOnCourseRepository.findByStudentAndCourse(student, course);
        if (studentRatingOnCourseOptional.isPresent()) {
            throw new IllegalStateException("Student " + usn + " has already rated the course " + courseId);
        }
        StudentRatingOnCourse studentRatingOnCourse = new StudentRatingOnCourse(student, course, Integer.parseInt(rating), review);
        studentRatingOnCourseRepository.save(studentRatingOnCourse);
    }

    public void deleteStudentRatingOnCourse(String usn, String courseId)    {
        Student student = studentService.getStudentByUsn(usn).orElseThrow(() -> new IllegalStateException("Student with USN " + usn + " does not exist."));
        Course course = courseService.getCourseById(courseId).orElseThrow(() -> new IllegalStateException(courseId + " course does not exist."));
        boolean exists = studentRatingOnCourseRepository.existsByStudentAndCourse(student, course);
        if (!exists)    {
            throw new IllegalStateException("Student " + usn + " haven't rated the course " + courseId + " yet.");
        }
        studentRatingOnCourseRepository.deleteByStudentAndCourse(student, course);
    }

    @Transactional
    public void updateStudentRatingOnCourse(String usn, String courseId, String rating, String review)  {
        Student student = studentService.getStudentByUsn(usn).orElseThrow(() -> new IllegalStateException("Student with USN " + usn + " does not exist."));
        Course course = courseService.getCourseById(courseId).orElseThrow(() -> new IllegalStateException(courseId + " course does not exist."));
        StudentRatingOnCourse studentRatingOnCourse = studentRatingOnCourseRepository.findByStudentAndCourse(student, course).orElseThrow(() -> new IllegalStateException("The student " + usn + " hasn't rated the course " + courseId + " yet."));
        if (review != null && review.length() > 0 && !Objects.equals(studentRatingOnCourse.getReview(), review))    {
            studentRatingOnCourse.setReview(review);
        }
        if (rating != null && rating.length() > 0 && !Objects.equals(studentRatingOnCourse.getRating(), Integer.parseInt(rating)))  {
            studentRatingOnCourse.setRating(Integer.parseInt(rating));
        }
    }
}
