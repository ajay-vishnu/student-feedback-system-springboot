package com.ajayv.studentfeedback.services;

import com.ajayv.studentfeedback.objects.Department;
import com.ajayv.studentfeedback.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public void addNewDepartment(Department department) {
        Optional<Department> departmentOptional = departmentRepository.findByName(department.getName());
        if (departmentOptional.isPresent()) {
            throw new IllegalStateException("Department Already Exists!");
        }
        departmentRepository.save(department);
    }

    public void deleteDepartment(String departmentName) {
        boolean exists = departmentRepository.existsByName(departmentName);
        if (!exists)    {
            throw new IllegalStateException(departmentName + " department does not exist.");
        }
        departmentRepository.deleteByName(departmentName);
    }

    @Transactional
    public void updateDepartment(String oldDepartmentName, String newDepartmentName) {
        Department department = departmentRepository.findByName(oldDepartmentName).orElseThrow(() -> new IllegalStateException(oldDepartmentName + "department does not exist."));
        if (newDepartmentName != null && !Objects.equals(department.getName(), newDepartmentName))  {
            department.setName(newDepartmentName);
        }
        else    {
            throw new IllegalStateException("Either the given department name is empty of it already exists.");
        }
    }
}
