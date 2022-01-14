package com.ajayv.studentfeedback.configuration;

import com.ajayv.studentfeedback.objects.Lecturer;
import com.ajayv.studentfeedback.repositories.LecturerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

//@Configuration
public class LecturerConfig {
//    @Bean
    CommandLineRunner commandLineRunner(LecturerRepository lecturerRepository)  {
        return args -> {
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
