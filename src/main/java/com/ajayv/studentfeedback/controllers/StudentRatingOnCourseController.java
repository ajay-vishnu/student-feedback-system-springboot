package com.ajayv.studentfeedback.controllers;

import com.ajayv.studentfeedback.json.StudentRatingOnCourseJson;
import com.ajayv.studentfeedback.objects.StudentRatingOnCourse;
import com.ajayv.studentfeedback.services.StudentRatingOnCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/studentRatingOnCourse")
public class StudentRatingOnCourseController {
    @Autowired
    StudentRatingOnCourseService studentRatingOnCourseService;

    @GetMapping
    public List<StudentRatingOnCourse> getStudentRatingOnCourses()  {
        return studentRatingOnCourseService.getStudentRatingOnCourses();
    }

    @PostMapping
    public void createNewStudentRatingOnCourse(@RequestBody StudentRatingOnCourseJson studentRatingOnCourseJson)  {
        studentRatingOnCourseService.addNewStudentRatingOnCourse(studentRatingOnCourseJson);
    }

    @DeleteMapping(path = "{courseId}/ratedBy/{studentId}")
    public void deleteStudentRatingOnCourse(@PathVariable("courseId") String courseId,
                                            @PathVariable("studentId") String usn,
                                            @RequestParam String updatedBy)  {
        studentRatingOnCourseService.deleteStudentRatingOnCourse(usn, courseId, updatedBy);
    }

    @PutMapping(path = "{courseId}/ratedBy/{studentId}")
    public void updateStudentRatingOnCourse(@PathVariable("courseId") String courseId,
                                            @PathVariable("studentId") String usn,
                                            @RequestParam String rating,
                                            @RequestParam String review,
                                            @RequestParam String updatedBy)    {
        studentRatingOnCourseService.updateStudentRatingOnCourse(usn, courseId, rating, review, updatedBy);
    }
}
