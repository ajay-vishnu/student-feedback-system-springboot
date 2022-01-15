package com.ajayv.studentfeedback.services;

import com.ajayv.studentfeedback.objects.Lecturer;
import com.ajayv.studentfeedback.repositories.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LecturerService {
    @Autowired
    private LecturerRepository lecturerRepository;

    public List<Lecturer> getLecturers()    {
        return lecturerRepository.findAll();
    }

    public Optional<Lecturer> getLecturersById(String lecturerId) {
        return lecturerRepository.findLecturerByLecturerId(lecturerId);
    }

    public void addNewLecturer(Lecturer lecturer)   {
        Optional<Lecturer> lecturerOptional = lecturerRepository.findLecturerByLecturerId(lecturer.getLecturerId());
        if (lecturerOptional.isPresent())   {
            throw new IllegalStateException("Lecturer Already Exists!");
        }
        lecturerRepository.save(lecturer);
    }

    public void deleteLecturer(String lecturerId)   {
        boolean exists = lecturerRepository.existsByLecturerId(lecturerId);
        if (!exists)    {
            throw new IllegalStateException("Lecturer with Lecturer ID " + lecturerId + " does not exist.");
        }
        lecturerRepository.deleteByLecturerId(lecturerId);
    }

    @Transactional
    public void updateLecturer(String lecturerId, String name, String phone)    {
        Lecturer lecturer = lecturerRepository.findLecturerByLecturerId(lecturerId).orElseThrow(() -> new IllegalStateException("Lecturer with lecturer ID " + lecturerId + " does not exist."));
        if (name != null && name.length() > 0 && !Objects.equals(lecturer.getName(), name)) {
            lecturer.setName(name);
        }
        if (phone != null && phone.length() > 0 && !Objects.equals(lecturer.getPhone(), phone)) {
            Optional<Lecturer> lecturerPhone = lecturerRepository.findLecturerByPhone(phone);
            if (lecturerPhone.isPresent())  {
                throw new IllegalStateException("This phone number has already been taken.");
            }
            lecturer.setPhone(phone);
        }
    }

    @Transactional
    public void assignDepartment(String lecturerId, String departmentId) {
        Lecturer lecturer = lecturerRepository.findLecturerByLecturerId(lecturerId).orElseThrow(() -> new IllegalStateException("Lecturer with ID " + lecturerId + " does not exist."));
    }
}
