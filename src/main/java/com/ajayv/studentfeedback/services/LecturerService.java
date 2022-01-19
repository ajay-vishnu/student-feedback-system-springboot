package com.ajayv.studentfeedback.services;

import com.ajayv.studentfeedback.json.LecturerJson;
import com.ajayv.studentfeedback.objects.Department;
import com.ajayv.studentfeedback.objects.Lecturer;
import com.ajayv.studentfeedback.repositories.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LecturerService {
    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private DepartmentService departmentService;

    public List<Lecturer> getLecturers()    {
        return lecturerRepository.findAllAndNotDeleted();
    }

    public Optional<Lecturer> getLecturersById(String lecturerId) {
        return lecturerRepository.findLecturerByLecturerId(lecturerId);
    }

    public void addNewLecturer(LecturerJson lecturerJson)   {
        Optional<Lecturer> lecturerOptional = lecturerRepository.findLecturerByLecturerId(lecturerJson.getLecturerId());
        if (lecturerOptional.isPresent())   {
            throw new IllegalStateException("Lecturer Already Exists!");
        }
        Lecturer lecturer = new Lecturer(
                lecturerJson.getLecturerId(),
                lecturerJson.getName(),
                lecturerJson.getPhone(),
                lecturerJson.getDob(),
                lecturerJson.getDateOfJoining(),
                lecturerJson.getPosition(),
                lecturerJson.getCreatedBy()
        );
        lecturerRepository.save(lecturer);
    }

    @Transactional
    public void deleteLecturer(String lecturerId, String deletedBy)   {
        Lecturer lecturer = lecturerRepository.findLecturerByLecturerId(lecturerId).orElseThrow(() -> new IllegalStateException("Lecturer with lecturer ID " + lecturerId + " does not exist."));
        lecturer.setUpdatedAt(LocalDateTime.now());
        lecturer.setUpdatedBy(deletedBy);
        lecturer.setDeleted(true);
    }

    @Transactional
    public void updateLecturer(String lecturerId, String name, String phone, String position, String departmentId, String updatedBy)    {
        if (updatedBy != null && updatedBy.length() > 0) {
            Lecturer lecturer = lecturerRepository.findLecturerByLecturerId(lecturerId).orElseThrow(() -> new IllegalStateException("Lecturer with lecturer ID " + lecturerId + " does not exist."));
            if (name != null && name.length() > 0 && !Objects.equals(lecturer.getName(), name)) {
                lecturer.setName(name);
            }
            if (position != null && position.length() > 0 && !Objects.equals(lecturer.getPosition(), position)) {
                lecturer.setPosition(position);
            }
            if (departmentId != null && departmentId.length() > 0) {
                Department department = departmentService.getDepartmentById(departmentId).orElseThrow(() -> new IllegalStateException(departmentId + " does not exist."));
                if (!Objects.equals(lecturer.getDepartment(), department))   {
                    lecturer.setDepartment(department);
                }
            }
            if (phone != null && phone.length() > 0 && !Objects.equals(lecturer.getPhone(), phone)) {
                Optional<Lecturer> lecturerPhone = lecturerRepository.findLecturerByPhone(phone);
                if (lecturerPhone.isPresent())  {
                    throw new IllegalStateException("This phone number has already been taken.");
                }
                lecturer.setPhone(phone);
            }
            lecturer.setUpdatedBy(updatedBy);
            lecturer.setUpdatedAt(LocalDateTime.now());
        }
        else    {
            throw new IllegalStateException("Must provide the updatedBy parameter to change values in database.");
        }
    }
}
