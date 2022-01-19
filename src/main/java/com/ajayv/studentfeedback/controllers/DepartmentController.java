package com.ajayv.studentfeedback.controllers;

import com.ajayv.studentfeedback.json.DepartmentJson;
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
    public void registerNewDepartment(@RequestBody DepartmentJson department)   {
        departmentService.addNewDepartment(department);
    }

    @DeleteMapping(path = "{departmentName}")
    public void deleteDepartment(
            @PathVariable("departmentName") String departmentName,
            @RequestParam String deletedBy
            ) {
        departmentService.deleteDepartment(departmentName, deletedBy);
    }

    @PutMapping(path = "{departmentName}")
    public void putDepartment(@PathVariable("departmentName") String oldDepartmentName,
                              @RequestParam(required = false) String newDepartmentName,
                              @RequestParam(required = false) String oldDepartmentId,
                              @RequestParam(required = false) String newDepartmentId,
                              @RequestParam(required = false) String updatedBy)   {
        departmentService.updateDepartment(oldDepartmentName, newDepartmentName, oldDepartmentId, newDepartmentId, updatedBy);
    }
}
