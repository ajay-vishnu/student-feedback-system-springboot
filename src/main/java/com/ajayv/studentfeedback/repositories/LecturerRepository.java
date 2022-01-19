package com.ajayv.studentfeedback.repositories;

import com.ajayv.studentfeedback.objects.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
    @Query("select l from Lecturer l where l.lecturerId=?1 and l.isDeleted=false")
    Optional<Lecturer> findLecturerByLecturerId(String lecturerId);

    @Query("select l from Lecturer l where l.phone=?1 and l.isDeleted=false")
    Optional<Lecturer> findLecturerByPhone(String phone);

    @Query("select l from Lecturer l where l.isDeleted=false")
    List<Lecturer> findAllAndNotDeleted();
}
