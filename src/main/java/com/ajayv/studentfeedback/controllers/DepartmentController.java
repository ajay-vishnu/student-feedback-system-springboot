package com.ajayv.studentfeedback.controllers;

import com.ajayv.studentfeedback.objects.Department;
import com.ajayv.studentfeedback.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<Department> getDepartments() {
        return this.departmentService.getDepartments();
    }

    @PostMapping
    public void registerNewDepartment(@RequestBody Department department)   {
        departmentService.addNewDepartment(department);
    }

    @DeleteMapping(path = "{departmentName}")
    public void deleteDepartment(@PathVariable("departmentName") String departmentName) {
        departmentService.deleteDepartment(departmentName);
    }

    @PutMapping(path = "{departmentName}")
    public void putDepartment(@PathVariable("departmentName") String oldDepartmentName,
                              @RequestParam String name)   {
        departmentService.updateDepartment(oldDepartmentName, name);
    }
}
