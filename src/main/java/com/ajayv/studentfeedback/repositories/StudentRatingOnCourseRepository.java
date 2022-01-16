package com.ajayv.studentfeedback.repositories;

import com.ajayv.studentfeedback.composite.StudentCourseComposite;
import com.ajayv.studentfeedback.objects.StudentRatingOnCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRatingOnCourseRepository extends JpaRepository<StudentRatingOnCourse, StudentCourseComposite> {
}
