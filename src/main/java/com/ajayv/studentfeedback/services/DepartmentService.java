package com.ajayv.studentfeedback.services;

import com.ajayv.studentfeedback.json.DepartmentJson;
import com.ajayv.studentfeedback.objects.Department;
import com.ajayv.studentfeedback.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> getDepartments()    {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(String departmentId) {
        return departmentRepository.findByIdAndNotDeleted(departmentId);
    }

    public void addNewDepartment(DepartmentJson departmentJson) {
        if (departmentJson.getCreatedBy() != null && departmentJson.getCreatedBy().length() > 0) {
            Optional<Department> departmentOptional = departmentRepository.findByName(departmentJson.getName());
            if (departmentOptional.isPresent()) {
                throw new IllegalStateException("Department Already Exists!");
            }
            Department department = new Department(
                    departmentJson.getDepartmentId(),
                    departmentJson.getName(),
                    departmentJson.getCreatedBy()
            );
            departmentRepository.save(department);
        }
        else    {
            throw new IllegalStateException("The createdBy parameter should be mentioned.");
        }
    }

    @Transactional
    public void deleteDepartment(String departmentName, String deletedBy) {
        if (deletedBy != null && deletedBy.length() > 0) {
            Department department = departmentRepository.findByName(departmentName).orElseThrow(() -> new IllegalStateException(departmentName + "department does not exist."));
            department.setDeleted(true);
            department.setUpdatedBy(deletedBy);
            department.setUpdatedAt(LocalDateTime.now());
        }

        else    {
            throw new IllegalStateException("Must mention the deletedBy parameter to update the database.");
        }
    }

    @Transactional
    public void updateDepartment(String oldDepartmentName, String newDepartmentName, String updatedBy) {
        if (updatedBy != null && updatedBy.length() > 0) {
            Department department = departmentRepository.findByName(oldDepartmentName).orElseThrow(() -> new IllegalStateException(oldDepartmentName + "department does not exist."));
            if (newDepartmentName != null && !Objects.equals(department.getName(), newDepartmentName))  {
                department.setName(newDepartmentName);
            }
            else    {
                throw new IllegalStateException("Either the given department name is empty of it already exists.");
            }
            department.setUpdatedBy(updatedBy);
            department.setUpdatedAt(LocalDateTime.now());
        }
        else    {
            throw new IllegalStateException("Must mention the updatedBy parameter to update the database.");
        }
    }
}
