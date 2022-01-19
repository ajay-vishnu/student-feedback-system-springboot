package com.ajayv.studentfeedback.controllers;

import com.ajayv.studentfeedback.json.CourseJson;
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

    @GetMapping(path = "getCourse/{courseId}")
    public Course getCourseById(@PathVariable("courseId") String courseId)  {
        return courseService.getCourseById(courseId).orElseThrow(() -> new IllegalStateException(courseId + "courseId does not exist."));
    }

    @PostMapping
    public void createNewCourse(@RequestBody CourseJson course) {
        courseService.addNewCourse(course);
    }

    @DeleteMapping(path = "{courseId}")
    public void deleteCourse(@PathVariable("courseId") String courseId,
                             @RequestParam String deletedBy) {
        courseService.deleteCourse(courseId, deletedBy);
    }

    @PutMapping(path = "{courseId}")
    public void putCourse(@PathVariable("courseId") String oldCourseId,
                          @RequestParam(required = false) String courseId,
                          @RequestParam(required = false) String courseName,
                          @RequestParam String updatedBy)  {
        courseService.updateCourse(oldCourseId, courseId, courseName, updatedBy);
    }

    @PutMapping(path = "{courseId}/taughtBy/{lecturerId}")
    public void assignLecturer(@PathVariable("courseId") String courseId,
                               @PathVariable("lecturerId") String lecturerId,
                               @RequestParam String updatedBy)   {
        courseService.assignLecturer(courseId, lecturerId, updatedBy);
    }

    @PutMapping(path = "{courseId}/belongsTo/{departmentId}")
    public void assignDepartment(@PathVariable("courseId") String courseId,
                                 @PathVariable("departmentId") String departmentId,
                                 @RequestParam String updatedBy) {
        courseService.assignDepartment(courseId, departmentId, updatedBy);
    }
}
