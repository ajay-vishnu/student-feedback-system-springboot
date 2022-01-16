package com.ajayv.studentfeedback.repositories;

import com.ajayv.studentfeedback.objects.Course;
import com.ajayv.studentfeedback.objects.Student;
import com.ajayv.studentfeedback.objects.StudentRatingOnCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRatingOnCourseRepository extends JpaRepository<StudentRatingOnCourse, Long> {
    Optional<StudentRatingOnCourse> findByStudentAndCourse(Student student, Course course);

    boolean existsByStudentAndCourse(Student student, Course course);

    void deleteByStudentAndCourse(Student student, Course course);
}
