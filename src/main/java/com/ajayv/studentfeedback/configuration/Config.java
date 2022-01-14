package com.ajayv.studentfeedback.configuration;

import com.ajayv.studentfeedback.objects.Lecturer;
import com.ajayv.studentfeedback.objects.Student;
import com.ajayv.studentfeedback.repositories.LecturerRepository;
import com.ajayv.studentfeedback.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;
import static java.time.Month.AUGUST;

@Configuration
public class Config {
    @Bean
    CommandLineRunner commandLineRunner (StudentRepository studentRepository, LecturerRepository lecturerRepository)  {
        return args ->  {
            Student abhay = new Student(
                    "4nn19cs001",
                    "Abhay Prabhu",
                    "9945390858",
                    LocalDate.of(2001, JANUARY, 19),
                    LocalDate.of(2001, AUGUST, 19),
                    "Mysuru"
            );
            Student abith = new Student(
                    "4nn19cs002",
                    "Abith Vainad",
                    "9746618923",
                    LocalDate.of(2001, OCTOBER, 19),
                    LocalDate.of(2001, AUGUST, 19),
                    "Vainad"
            );
            Student aditya = new Student(
                    "4nn19cs003",
                    "Aditya Mys",
                    "9741885368",
                    LocalDate.of(1999, MARCH, 19),
                    LocalDate.of(2001, AUGUST, 19),
                    "Mysuru"
            );
            Student prajwal = new Student(
                    "4nn19cs022",
                    "Prajwal K B",
                    "9482927308",
                    LocalDate.of(2001, MARCH, 30),
                    LocalDate.of(2001, AUGUST, 19),
                    "Mandya"
            );
            Student ajay = new Student(
                    "4nn19cs005",
                    "Ajay Vishnu",
                    "9066575399",
                    LocalDate.of(2001, JANUARY, 4),
                    LocalDate.of(2001, AUGUST, 19),
                    "Davangere"
            );
            studentRepository.saveAll(List.of(abhay, abith, aditya, prajwal, ajay));
            Lecturer usha = new Lecturer(
                    "4niecs01",
                    "Ms. Usha M S",
                    "821274231",
                    LocalDate.of(1970, Month.JANUARY, 1),
                    LocalDate.of(2008, Month.OCTOBER, 1)
            );
            Lecturer veda = new Lecturer(
                    "4niecs02",
                    "Ms. Vedavathi N",
                    "821274232",
                    LocalDate.of(1985, Month.MARCH, 1),
                    LocalDate.of(2016, Month.FEBRUARY, 1)
            );
            Lecturer prema = new Lecturer(
                    "4niecs03",
                    "Ms. Prema N",
                    "9986678739",
                    LocalDate.of(1980, Month.SEPTEMBER, 1),
                    LocalDate.of(2012, Month.APRIL, 1)
            );
            Lecturer madhusudhan = new Lecturer(
                    "4niecs04",
                    "Mr. Madhusudhan H S",
                    "821274234",
                    LocalDate.of(1970, Month.DECEMBER, 1),
                    LocalDate.of(2015, Month.NOVEMBER, 1)
            );
            Lecturer roopa = new Lecturer(
                    "4niecs05",
                    "Ms. Roopa S N",
                    "9742188727",
                    LocalDate.of(1987, Month.AUGUST, 1),
                    LocalDate.of(2019, Month.JUNE, 1)
            );
            lecturerRepository.saveAll(List.of(usha, veda, prema, madhusudhan, roopa));
        };
    }
}
