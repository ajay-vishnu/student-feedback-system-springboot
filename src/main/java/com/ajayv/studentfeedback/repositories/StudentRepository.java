package com.ajayv.studentfeedback.repositories;

import com.ajayv.studentfeedback.objects.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student s where s.usn=?1 and s.isDeleted=false")
    Optional<Student> findStudentByUsn(String usn);

    @Query("select s from Student s where s.phone=?1 and s.isDeleted=false")
    Optional<Student> findStudentByPhone(String phone);

    @Query("select s from Student s where s.isDeleted=false")
    List<Student> findAllAndNotIsDeleted();
}