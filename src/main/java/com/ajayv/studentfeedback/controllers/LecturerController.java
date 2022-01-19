package com.ajayv.studentfeedback.controllers;

import com.ajayv.studentfeedback.objects.Lecturer;
import com.ajayv.studentfeedback.services.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/lecturer")
public class LecturerController {
    @Autowired
    private LecturerService lecturerService;

    @GetMapping
    public List<Lecturer> getLecturers() {
        return this.lecturerService.getLecturers();
    }

    @PostMapping
    public void registerNewLecturer(@RequestBody Lecturer lecturer) {
        lecturerService.addNewLecturer(lecturer);
    }

    @DeleteMapping(path = "{lecturerId}")
    public void deleteStudent(@PathVariable("lecturerId") String lecturerId)    {
        lecturerService.deleteLecturer(lecturerId);
    }

    @PutMapping(path = "{lecturerId}")
    public void putLecturer(@PathVariable("lecturerId") String lecturerId,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String phone,
                            @RequestParam(required = false) String position,
                            @RequestParam(required = false) String departmentId)   {
        lecturerService.updateLecturer(lecturerId, name, phone, position, departmentId);
    }
}
