package com.ajayv.studentfeedback.services;

import com.ajayv.studentfeedback.objects.Course;
import com.ajayv.studentfeedback.objects.Department;
import com.ajayv.studentfeedback.objects.Lecturer;
import com.ajayv.studentfeedback.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    LecturerService lecturerService;

    @Autowired
    DepartmentService departmentService;

    public List<Course> getCourses()    {
        return this.courseRepository.findAll();
    }

    public void addNewCourse(Course course) {
        Optional<Course> courseOptional = courseRepository.findById(course.getId());
        if (courseOptional.isPresent()) {
            throw new IllegalStateException("Course already Exists!");
        }
        courseRepository.save(course);
    }

    public void deleteCourse(String courseId) {
        boolean exists = courseRepository.existsById(courseId);
        if (!exists)    {
            throw new IllegalStateException("Course " + courseId + " does not exist.");
        }
        courseRepository.deleteById(courseId);
    }

    @Transactional
    public void updateCourse(String oldCourseId, String newCourseId, String courseName) {
        Course course = courseRepository.findById(oldCourseId).orElseThrow(() -> new IllegalStateException("The course " + oldCourseId + " does not exist."));
        if (courseName != null && !Objects.equals(course.getName(), courseName))  {
            course.setName(courseName);
        }
        if (newCourseId != null && !Objects.equals(course.getId(), newCourseId))    {
            Optional<Course> courseId = courseRepository.findById(newCourseId);
            if (courseId.isPresent())   {
                throw new IllegalStateException("This course ID is already present.");
            }
            course.setId(newCourseId);
        }
    }

    @Transactional
    public void assignLecturer(String courseId, String lecturerId)   {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalStateException(courseId + " does not exist."));
        Lecturer lecturer = lecturerService.getLecturersById(lecturerId).orElseThrow(() -> new IllegalStateException("Lecturer with ID " + lecturerId + " does not exist."));
        if (lecturerId != null && !Objects.equals(course.getLecturer(), lecturer))    {
            course.setLecturer(lecturer);
            courseRepository.save(course);
        }
    }

    @Transactional
    public void assignDepartment(String courseId, String departmentId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalStateException(courseId + " does not exist."));
        Department department = departmentService.getDepartmentById(departmentId).orElseThrow(() -> new IllegalStateException(departmentId + " does not exist."));
        if (departmentId != null && !Objects.equals(course.getDepartment(), departmentId))  {
            course.setDepartment(department);
            courseRepository.save(course);
        }
    }
}
