package com.ajayv.studentfeedback.repositories;

import com.ajayv.studentfeedback.objects.Course;
import com.ajayv.studentfeedback.objects.Student;
import com.ajayv.studentfeedback.objects.StudentRatingOnCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRatingOnCourseRepository extends JpaRepository<StudentRatingOnCourse, Long> {
    @Query("select s from StudentRatingOnCourse s where s.student=?1 and s.course=?2 and s.isDeleted=false")
    Optional<StudentRatingOnCourse> findByStudentAndCourse(Student student, Course course);
}
