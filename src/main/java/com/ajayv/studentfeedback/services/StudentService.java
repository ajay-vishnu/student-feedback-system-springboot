package com.ajayv.studentfeedback.services;

import com.ajayv.studentfeedback.json.StudentJson;
import com.ajayv.studentfeedback.objects.Student;
import com.ajayv.studentfeedback.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents()  {
        return studentRepository.findAllAndNotIsDeleted();
    }

    public Optional<Student> getStudentByUsn(String usn)  {
        return studentRepository.findStudentByUsn(usn);
    }

    public void addNewStudent(StudentJson studentJson)  {
        Student student = new Student(
                studentJson.getUsn(),
                studentJson.getName(),
                studentJson.getPhone(),
                studentJson.getDob(),
                studentJson.getDateOfJoining(),
                studentJson.getLocation(),
                studentJson.getCreatedBy()
        );
        Optional<Student> studentOptional = studentRepository.findStudentByUsn(student.getUsn());
        if (studentOptional.isPresent())    {
            throw new IllegalStateException("Student Already Exists!");
        }
        studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(String usn, String deletedBy)   {
        Student student = studentRepository.findStudentByUsn(usn).orElseThrow(() -> new IllegalStateException("Student with USN " + usn + " does not exist."));
        student.setUpdatedBy(deletedBy);
        student.setDeleted(true);
    }

    @Transactional
    public void updateStudent(String usn, String updatedBy, String name, String phone)    {
        Student student = studentRepository.findStudentByUsn(usn).orElseThrow(() -> new IllegalStateException("Student with USN " + usn + " does not exist."));
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name))  {
            student.setName(name);
        }
        if (phone != null && phone.length() > 0 && !Objects.equals(student.getPhone(), phone))  {
            Optional<Student> studentPhone = studentRepository.findStudentByPhone(phone);
            if (studentPhone.isPresent())   {
                throw new IllegalStateException("This phone number has already been taken.");
            }
            student.setPhone(phone);
        }
        if (updatedBy != null && updatedBy.length() > 0)    {
            student.setUpdatedBy(updatedBy);
            student.setUpdatedAt(LocalDateTime.now());
        }
    }

}
