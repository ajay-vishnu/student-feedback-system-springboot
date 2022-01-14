package com.ajayv.studentfeedback.repositories;

import com.ajayv.studentfeedback.objects.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);

    boolean existsByName(String departmentName);

    @Transactional
    @Modifying
    @Query("delete from Department d where d.name=?1")
    void deleteByName(String departmentName);
}
