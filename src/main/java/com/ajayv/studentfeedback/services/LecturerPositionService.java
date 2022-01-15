package com.ajayv.studentfeedback.services;

import com.ajayv.studentfeedback.objects.Department;
import com.ajayv.studentfeedback.objects.Lecturer;
import com.ajayv.studentfeedback.objects.LecturerPosition;
import com.ajayv.studentfeedback.repositories.LecturerPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LecturerPositionService {
    @Autowired
    private LecturerPositionRepository lecturerPositionRepository;

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private DepartmentService departmentService;

    public List<LecturerPosition> getLecturerPositions()    {
        return lecturerPositionRepository.findAll();
    }

    public Optional<LecturerPosition> getLecturerPositionByLecturerId(String lecturerId)    {
        Lecturer lecturer = lecturerService.getLecturersById(lecturerId).orElseThrow(() -> new IllegalStateException("Lecturer with ID " + lecturerId + " does not exist."));
        return lecturerPositionRepository.findByLecturer(lecturer);
    }

    public void addNewLecturerPosition(String departmentId, String lecturerId, String position)   {
        Lecturer lecturer = lecturerService.getLecturersById(lecturerId).orElseThrow(() -> new IllegalStateException("Lecturer with ID " + lecturerId + " does not exist."));
        Department department = departmentService.getDepartmentById(departmentId).orElseThrow(() -> new IllegalStateException(departmentId + " department does not exist."));
        Optional<LecturerPosition> lecturerPositionOptional = lecturerPositionRepository.findByLecturer(lecturer);
        if (lecturerPositionOptional.isPresent())   {
            throw new IllegalStateException("Lecturer position has already been assigned.");
        }
        LecturerPosition lecturerPosition = new LecturerPosition(lecturer, department, position);
        lecturerPositionRepository.save(lecturerPosition);
    }

    public void deleteLecturerPosition(String lecturerId)   {
        Lecturer lecturer = lecturerService.getLecturersById(lecturerId).orElseThrow(() -> new IllegalStateException("Lecturer with ID " + lecturerId + " does not exist."));
        boolean exists = lecturerPositionRepository.existsByLecturer(lecturer);
        if (!exists)    {
            throw new IllegalStateException("Lecturer with ID" + lecturerId + " does not have any position.");
        }
        lecturerPositionRepository.deleteByLecturer(lecturer);
    }

    @Transactional
    public void updateLecturerPosition(String lecturerId, String departmentId, String position) {
        Lecturer lecturer = lecturerService.getLecturersById(lecturerId).orElseThrow(() -> new IllegalStateException("Lecturer with ID " + lecturerId + " does not exist."));
        LecturerPosition lecturerPosition = lecturerPositionRepository.findByLecturer(lecturer).orElseThrow(() -> new IllegalStateException("Lecture with ID" + lecturerId + " does not have a position yet."));
        if (position != null && position.length() > 0 && !Objects.equals(lecturerPosition.getPosition(), position)) {
            lecturerPosition.setPosition(position);
        }
        if (departmentId != null && departmentId.length() > 0 && !Objects.equals(lecturerPosition.getDepartment().getDepartmentId(), departmentId)) {
            Department department = departmentService.getDepartmentById(departmentId).orElseThrow(() -> new IllegalStateException(departmentId + " department does not exist."));
            lecturerPosition.setDepartment(department);
        }
    }
}
