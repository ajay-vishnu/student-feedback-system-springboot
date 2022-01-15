package com.ajayv.studentfeedback.repositories;

import com.ajayv.studentfeedback.objects.Lecturer;
import com.ajayv.studentfeedback.objects.LecturerPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface LecturerPositionRepository extends JpaRepository<LecturerPosition, Long> {
    Optional<LecturerPosition> findByLecturer(Lecturer lecturer);

    boolean existsByLecturer(Lecturer lecturer);

    void deleteByLecturer(Lecturer lecturer);
}
