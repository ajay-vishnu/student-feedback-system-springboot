package com.ajayv.studentfeedback.configuration;

import com.ajayv.studentfeedback.objects.Student;
import com.ajayv.studentfeedback.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

//@Configuration
public class StudentConfig {
//    @Bean
    CommandLineRunner commandLineRunner (StudentRepository repository)  {
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
            repository.saveAll(List.of(abhay, abith, aditya, prajwal, ajay));
        };
    }
}
