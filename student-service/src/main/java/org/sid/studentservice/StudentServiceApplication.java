package org.sid.studentservice;

import org.sid.studentservice.entities.Student;
import org.sid.studentservice.repositories.StudentRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class StudentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(StudentRepo studentRepository, RepositoryRestConfiguration restConfiguration) {
        // Expose entity IDs in REST API responses
        restConfiguration.exposeIdsFor(Student.class);

        return args -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            List<Student> students = List.of(
                    new Student(null, "KM06515SE", "Kwame", "M'Vomo", sdf.parse("2006-05-15"), "Secondary", null),
                    new Student(null, "MB10412SE", "Moumi", "Bongo", sdf.parse("2010-04-12"), "Secondary", null),
                    new Student(null, "NN19821PR", "Nadine", "Ngouabi", sdf.parse("2013-08-21"), "Primary", null),
                    new Student(null, "EB18108PR", "Elvis", "Biyogo", sdf.parse("2005-10-08"), "Primary", null)
            );

            // Save students to the database
            studentRepository.saveAll(students);

            // Output the saved students to the console
            studentRepository.findAll().forEach(System.out::println);
        };
    }
}
