package com.ajayv.studentfeedback.controllers;

import com.ajayv.studentfeedback.objects.Course;
import com.ajayv.studentfeedback.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> getCourses()    {
        return courseService.getCourses();
    }

    @PostMapping
    public void createNewCourse(@RequestBody Course course) {
        courseService.addNewCourse(course);
    }

    @DeleteMapping(path = "{courseId}")
    public void deleteCourse(@PathVariable("courseId") String courseId) {
        courseService.deleteCourse(courseId);
    }

    @PutMapping(path = "{courseId}")
    public void putCourse(@PathVariable("courseId") String oldCourseId,
                          @RequestParam String courseId,
                          @RequestParam String courseName)  {
        courseService.updateCourse(oldCourseId, courseId, courseName);
    }

    @PutMapping(path = "{courseId}/taughtBy/{lecturerId}")
    public void assignLecturer(@PathVariable("courseId") String courseId,
                               @PathVariable("lecturerId") String lecturerId)   {
        courseService.assignLecturer(courseId, lecturerId);
    }

    @PutMapping(path = "{courseId}/belongsTo/{departmentId}")
    public void assignDepartment(@PathVariable("courseId") String courseId,
                                 @PathVariable("departmentId") String departmentId) {
        courseService.assignDepartment(courseId, departmentId);
    }
}
