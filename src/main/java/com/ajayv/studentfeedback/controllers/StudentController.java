package com.ajayv.studentfeedback.controllers;

import com.ajayv.studentfeedback.json.StudentJson;
import com.ajayv.studentfeedback.objects.Student;
import com.ajayv.studentfeedback.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getStudents() {
        return this.studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody StudentJson student)    {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentUsn}")
    public void deleteStudent(@PathVariable("studentUsn") String usn, @RequestParam String deletedBy)   {
        studentService.deleteStudent(usn, deletedBy);
    }

    @PutMapping(path = "{studentUsn}")
    public void putStudent(@PathVariable("studentUsn") String usn,
                           @RequestParam String updatedBy,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String phone)   {
        studentService.updateStudent(usn, updatedBy, name, phone);
    }
}
