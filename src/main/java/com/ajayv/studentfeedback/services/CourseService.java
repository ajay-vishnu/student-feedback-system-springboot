package com.ajayv.studentfeedback.services;

import com.ajayv.studentfeedback.json.CourseJson;
import com.ajayv.studentfeedback.objects.Course;
import com.ajayv.studentfeedback.objects.Department;
import com.ajayv.studentfeedback.objects.Lecturer;
import com.ajayv.studentfeedback.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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
        return this.courseRepository.findAllAndNotDeleted();
    }

    public Optional<Course> getCourseById(String courseId) {
        return courseRepository.findByCourseId(courseId);
    }

    public void addNewCourse(CourseJson courseJson) {
        if (courseJson.getCreatedBy() != null && courseJson.getCreatedBy().length() > 0)    {
            Lecturer lecturer = null;
            Department department = null;
            Optional<Course> courseOptional = courseRepository.findByCourseId(courseJson.getCourseId());
            if (courseOptional.isPresent()) {
                throw new IllegalStateException("Course already Exists!");
            }
            if (courseJson.getLecturerId() != null && courseJson.getLecturerId().length() > 0)  {
                lecturer = lecturerService.getLecturersById(courseJson.getLecturerId()).orElseThrow(() -> new IllegalStateException("Lecturer with ID " + courseJson.getLecturerId() + " does not exist."));
            }
            if (courseJson.getDepartmentId() != null && courseJson.getDepartmentId().length() > 0) {
                department = departmentService.getDepartmentById(courseJson.getDepartmentId()).orElseThrow(() -> new IllegalStateException(courseJson.getDepartmentId() + " department does not exist."));
            }
            Course course = new Course(courseJson.getCourseId(), courseJson.getName(), lecturer, department, courseJson.getCreatedBy());
            courseRepository.save(course);
        }
        else    {
            throw new IllegalStateException("Must mention the createdBy parameter to update the database.");
        }
    }

    @Transactional
    public void deleteCourse(String courseId, String deletedBy) {
        if (deletedBy != null && deletedBy.length() > 0) {
            Course course = courseRepository.findByCourseId(courseId).orElseThrow(() -> new IllegalStateException(courseId + " course does not exist."));
            course.setUpdatedBy(deletedBy);
            course.setUpdatedAt(LocalDateTime.now());
            course.setDeleted(true);
        }
        else    {
            throw new IllegalStateException("Must mention the deletedBy parameter to update the database.");
        }
    }

    @Transactional
    public void updateCourse(String oldCourseId, String newCourseId, String courseName, String updatedBy) {
        if (updatedBy != null && updatedBy.length() > 0) {
            boolean flag = false;
            Course course = courseRepository.findByCourseId(oldCourseId).orElseThrow(() -> new IllegalStateException("The course " + oldCourseId + " does not exist."));
            if (courseName != null && courseName.length() > 0 && !Objects.equals(course.getName(), courseName))  {
                course.setName(courseName);
                flag = true;
            }
            if (newCourseId != null && !Objects.equals(course.getCourseId(), newCourseId))    {
                Optional<Course> courseId = courseRepository.findByCourseId(newCourseId);
                if (courseId.isPresent())   {
                    throw new IllegalStateException("This course ID is already present.");
                }
                course.setCourseId(newCourseId);
                flag = true;
            }
            if (flag) {
                course.setUpdatedAt(LocalDateTime.now());
                course.setUpdatedBy(updatedBy);
            }
            else    {
                throw new IllegalStateException("Must mention the updatedBy parameter to update the database.");
            }
        }
    }

    @Transactional
    public void assignLecturer(String courseId, String lecturerId, String updatedBy)   {
        if (updatedBy != null && updatedBy.length() > 0) {
            Course course = courseRepository.findByCourseId(courseId).orElseThrow(() -> new IllegalStateException(courseId + " does not exist."));
            Lecturer lecturer = lecturerService.getLecturersById(lecturerId).orElseThrow(() -> new IllegalStateException("Lecturer with ID " + lecturerId + " does not exist."));
            if (lecturerId != null && lecturerId.length() > 0 && !Objects.equals(course.getLecturer(), lecturer))    {
                course.setLecturer(lecturer);
                courseRepository.save(course);
            }
            course.setUpdatedBy(updatedBy);
            course.setUpdatedAt(LocalDateTime.now());
        }
        else    {
            throw new IllegalStateException("Must mention the updatedBy parameter to update the database.");
        }
    }

    @Transactional
    public void assignDepartment(String courseId, String departmentId, String updatedBy) {
        if (updatedBy != null && updatedBy.length() > 0) {
            boolean flag = false;
            Course course = courseRepository.findByCourseId(courseId).orElseThrow(() -> new IllegalStateException(courseId + " does not exist."));
            if (departmentId != null && departmentId.length() > 0 && !Objects.equals(course.getDepartment(), departmentId))  {
                Department department = departmentService.getDepartmentById(departmentId).orElseThrow(() -> new IllegalStateException(departmentId + " does not exist."));
                course.setDepartment(department);
                flag = true;
            }
            if (flag) {
                course.setUpdatedBy(updatedBy);
                course.setUpdatedAt(LocalDateTime.now());
            }
        }
    }
}
