package com.ajayv.studentfeedback.configuration;

import com.ajayv.studentfeedback.objects.*;
import com.ajayv.studentfeedback.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class Config {
    @Bean
    CommandLineRunner commandLineRunner (StudentRepository studentRepository,
                                         LecturerRepository lecturerRepository,
                                         CourseRepository courseRepository,
                                         DepartmentRepository departmentRepository)  {
        return args ->  {
            Student abhay = new Student(
                    "4nn19cs001",
                    "Abhay Prabhu",
                    "9945390858",
                    LocalDate.of(2001, JANUARY, 19),
                    LocalDate.of(2001, AUGUST, 19),
                    "Mysuru",
                    "Ajay Vishnu"
            );
            Student abith = new Student(
                    "4nn19cs002",
                    "Abith Vainad",
                    "9746618923",
                    LocalDate.of(2001, OCTOBER, 19),
                    LocalDate.of(2001, AUGUST, 19),
                    "Vainad",
                    "Ajay Vishnu"
            );
            Student aditya = new Student(
                    "4nn19cs003",
                    "Aditya Mys",
                    "9741885368",
                    LocalDate.of(1999, MARCH, 19),
                    LocalDate.of(2001, AUGUST, 19),
                    "Mysuru",
                    "Ajay Vishnu"
            );
            Student prajwal = new Student(
                    "4nn19cs022",
                    "Prajwal K B",
                    "9482927308",
                    LocalDate.of(2001, MARCH, 30),
                    LocalDate.of(2001, AUGUST, 19),
                    "Mandya",
                    "Ajay Vishnu"
            );
            Student ajay = new Student(
                    "4nn19cs005",
                    "Ajay Vishnu",
                    "9066575399",
                    LocalDate.of(2001, JANUARY, 4),
                    LocalDate.of(2001, AUGUST, 19),
                    "Davangere",
                    "Ajay Vishnu"
            );
            studentRepository.saveAll(List.of(abhay, abith, aditya, prajwal, ajay));
            Course c1 = new Course(
                    "18CS51",
                    "Management, Entrepreneurship for IT Industry"
            );
            Course c2 = new Course(
                    "18CS52",
                    "Computer Networks and Security"
            );
            Course c3 = new Course(
                    "18CS53",
                    "Database Management System"
            );
            Course c4 = new Course(
                    "18CS54",
                    "Automata theory and Computability"
            );
            Course c5 = new Course(
                    "18CS55",
                    "Application Development using Python"
            );
            Course c6 = new Course(
                    "18CS56",
                    "Unix Programming"
            );
            Course c7 = new Course(
                    "18CSL57",
                    "Computer Network Laboratory"
            );
            Course c8 = new Course(
                    "18CSL58",
                    "DBMS Laboratory with mini project"
            );
            Course c9 = new Course(
                    "18CIV59",
                    "Environmental Studies"
            );
            courseRepository.saveAll(List.of(c1, c2, c3, c4, c5, c6, c7, c8, c9));
            Lecturer usha = new Lecturer(
                    "4niecs01",
                    "Ms. Usha M S",
                    "821274231",
                    LocalDate.of(1970, Month.JANUARY, 1),
                    LocalDate.of(2008, Month.OCTOBER, 1),
                    "Ajay Vishnu"
            );
            Lecturer veda = new Lecturer(
                    "4niecs02",
                    "Ms. Vedavathi N",
                    "821274232",
                    LocalDate.of(1985, Month.MARCH, 1),
                    LocalDate.of(2016, Month.FEBRUARY, 1),
                    "Ajay Vishnu"
            );
            Lecturer prema = new Lecturer(
                    "4niecs03",
                    "Ms. Prema N",
                    "9986678739",
                    LocalDate.of(1980, Month.SEPTEMBER, 1),
                    LocalDate.of(2012, Month.APRIL, 1),
                    "Ajay Vishnu"
            );
            Lecturer madhusudhan = new Lecturer(
                    "4niecs04",
                    "Mr. Madhusudhan H S",
                    "821274234",
                    LocalDate.of(1970, Month.DECEMBER, 1),
                    LocalDate.of(2015, Month.NOVEMBER, 1),
                    "Ajay Vishnu"
            );
            Lecturer roopa = new Lecturer(
                    "4niecs05",
                    "Ms. Roopa S N",
                    "9742188727",
                    LocalDate.of(1987, Month.AUGUST, 1),
                    LocalDate.of(2019, Month.JUNE, 1),
                    "Ajay Vishnu"
            );
            lecturerRepository.saveAll(List.of(usha, veda, prema, madhusudhan, roopa));
            Department cse = new Department("CSE", "COMPUTER SCIENCE AND ENGINEERING", "Ajay Vishnu");
            Department ise = new Department("ISE", "INFORMATION SCIENCE AND ENGINEERING", "Ajay Vishnu");
            Department ece = new Department("ECE", "ELECTRONICS AND COMMUNICATION ENGINEERING", "Ajay Vishnu");
            Department eee = new Department("EEE", "ELECTRICAL AND ELECTRONICS ENGINEERING", "Ajay Vishnu");
            Department mec = new Department("ME", "MECHANICAL ENGINEERING", "Ajay Vishnu");
            departmentRepository.saveAll(List.of(cse, ise, ece, eee, mec));
        };
    }
}
