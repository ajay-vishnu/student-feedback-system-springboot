package com.ajayv.studentfeedback.repositories;

import com.ajayv.studentfeedback.objects.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
}
