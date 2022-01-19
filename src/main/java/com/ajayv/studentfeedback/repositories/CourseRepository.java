package com.ajayv.studentfeedback.repositories;

import com.ajayv.studentfeedback.objects.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("select c from Course c where c.isDeleted=false")
    List<Course> findAllAndNotDeleted();

    Optional<Course> findByCourseId(String courseId);

    boolean existsByCourseId(String courseId);

    void deleteByCourseId(String courseId);
}
