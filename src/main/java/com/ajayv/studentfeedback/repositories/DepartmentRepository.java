package com.ajayv.studentfeedback.repositories;

import com.ajayv.studentfeedback.objects.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("select d from Department d where d.isDeleted=false")
    List<Department> findByIdAndNotDeleted();

    @Query("select d from Department d where d.departmentId=?1 and d.isDeleted=false")
    Optional<Department> findByDepartmentId(String departmentId);
}
