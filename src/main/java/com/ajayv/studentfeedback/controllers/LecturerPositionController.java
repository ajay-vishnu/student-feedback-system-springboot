package com.ajayv.studentfeedback.controllers;

import com.ajayv.studentfeedback.objects.LecturerPosition;
import com.ajayv.studentfeedback.objects.LecturerPositionDetails;
import com.ajayv.studentfeedback.services.LecturerPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/lecturerPosition")
public class LecturerPositionController {
    @Autowired
    private LecturerPositionService lecturerPositionService;

    @GetMapping
    public List<LecturerPosition> getLecturerPositions() {
        return this.lecturerPositionService.getLecturerPositions();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody LecturerPositionDetails lecturerPositionDetails)    {
        lecturerPositionService.addNewLecturerPosition(lecturerPositionDetails.getDepartmentId(), lecturerPositionDetails.getLecturerId(), lecturerPositionDetails.getPosition());
    }

    @DeleteMapping(path = "{lecturerId}")
    public void deleteLecturerPosition(@PathVariable("lecturerId") String lecturerId)   {
        lecturerPositionService.deleteLecturerPosition(lecturerId);
    }

    @PutMapping(path = "{lecturerId}")
    public void putStudent(@PathVariable("lecturerId") String lecturerId,
                           @RequestParam(required = false) String departmentId,
                           @RequestParam(required = false) String position)   {
        lecturerPositionService.updateLecturerPosition(lecturerId, departmentId, position);
    }
}
