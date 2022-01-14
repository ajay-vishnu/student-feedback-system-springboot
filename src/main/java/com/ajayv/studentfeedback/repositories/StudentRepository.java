package com.ajayv.studentfeedback.repositories;

import com.ajayv.studentfeedback.objects.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findStudentByUsn(String usn);

    boolean existsByUsn(String usn);

    @Transactional
    @Modifying
    @Query("delete from Student s where s.usn=?1")
    void deleteByUsn(String usn);

    Optional<Student> findStudentByPhone(String phone);
}