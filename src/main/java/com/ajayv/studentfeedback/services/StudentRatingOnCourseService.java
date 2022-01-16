package com.ajayv.studentfeedback.services;

import com.ajayv.studentfeedback.composite.StudentCourseComposite;
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
        StudentCourseComposite studentCourseComposite = new StudentCourseComposite(student, course);
        return studentRatingOnCourseRepository.findById(studentCourseComposite);
    }

    public void addNewStudentRatingOnCourse(String usn, String courseId, String rating, String review)  {
        Student student = studentService.getStudentByUsn(usn).orElseThrow(() -> new IllegalStateException("Student with USN " + usn + " does not exist."));
        Course course = courseService.getCourseById(courseId).orElseThrow(() -> new IllegalStateException(courseId + " course does not exist."));
        StudentCourseComposite studentCourseComposite = new StudentCourseComposite(student, course);
        Optional<StudentRatingOnCourse> studentRatingOnCourseOptional = studentRatingOnCourseRepository.findById(studentCourseComposite);
        if (studentRatingOnCourseOptional.isPresent()) {
            throw new IllegalStateException("Student " + usn + " has already rated the course " + courseId);
        }
        StudentRatingOnCourse studentRatingOnCourse = new StudentRatingOnCourse(student, course, Integer.parseInt(rating), review);
        studentRatingOnCourseRepository.save(studentRatingOnCourse);
    }

    public void deleteStudentRatingOnCourse(String usn, String courseId)    {
        Student student = studentService.getStudentByUsn(usn).orElseThrow(() -> new IllegalStateException("Student with USN " + usn + " does not exist."));
        Course course = courseService.getCourseById(courseId).orElseThrow(() -> new IllegalStateException(courseId + " course does not exist."));
        StudentCourseComposite studentCourseComposite = new StudentCourseComposite(student, course);
        boolean exists = studentRatingOnCourseRepository.existsById(studentCourseComposite);
        if (!exists)    {
            throw new IllegalStateException("Student " + usn + " haven't rated the course " + courseId + " yet.");
        }
        studentRatingOnCourseRepository.deleteById(studentCourseComposite);
    }

    @Transactional
    public void updateStudentRatingOnCourse(String usn, String courseId, String rating, String review)  {
        Student student = studentService.getStudentByUsn(usn).orElseThrow(() -> new IllegalStateException("Student with USN " + usn + " does not exist."));
        Course course = courseService.getCourseById(courseId).orElseThrow(() -> new IllegalStateException(courseId + " course does not exist."));
        StudentCourseComposite studentCourseComposite = new StudentCourseComposite(student, course);
        StudentRatingOnCourse studentRatingOnCourse = studentRatingOnCourseRepository.findById(studentCourseComposite).orElseThrow(() -> new IllegalStateException("The student " + usn + " hasn't rated the course " + courseId + " yet."));
        if (review != null && review.length() > 0 && !Objects.equals(studentRatingOnCourse.getReview(), review))    {
            studentRatingOnCourse.setReview(review);
        }
        if (rating != null && rating.length() > 0 && !Objects.equals(studentRatingOnCourse.getRating(), Integer.parseInt(rating)))  {
            studentRatingOnCourse.setRating(Integer.parseInt(rating));
        }
    }
}
