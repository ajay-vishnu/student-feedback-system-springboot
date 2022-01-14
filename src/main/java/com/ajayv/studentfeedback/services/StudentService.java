package com.ajayv.studentfeedback.services;

import com.ajayv.studentfeedback.objects.Student;
import com.ajayv.studentfeedback.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudents()  {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student)  {
        Optional<Student> studentOptional = studentRepository.findStudentByUsn(student.getUsn());
        if (studentOptional.isPresent())    {
            throw new IllegalStateException("Student Already Exists!");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(String usn)   {
        boolean exists = studentRepository.existsByUsn(usn);
        if (!exists)    {
            throw new IllegalStateException("Student with USN " + usn + " does not exist.");
        }
        studentRepository.deleteByUsn(usn);
    }

    @Transactional
    public void updateStudent(String usn, String name, String phone)    {
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
    }

}
