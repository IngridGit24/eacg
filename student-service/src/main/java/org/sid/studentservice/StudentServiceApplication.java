package org.sid.studentservice;

import org.sid.studentservice.entities.Student;
import org.sid.studentservice.repositories.StudentRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class StudentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(StudentRepo studentRepository, RepositoryRestConfiguration restConfiguration) {
        // Expose the ID for the Student entity in the REST API
        restConfiguration.exposeIdsFor(Student.class);

        return args -> {
            // Saving a list of student records to the database
            studentRepository.saveAll(
                    List.of(
                            new Student(null, "KM06515SE", "Kwame", "M'Vomo", new Date(2006 - 1900, 5 - 1, 15), "Secondary", null),
                            new Student(null, "MB10412SE", "Moumi", "Bongo", new Date(2010 - 1900, 4 - 1, 12), "Secondary", null),
                            new Student(null, "NN19821PR", "Nadine", "Ngouabi", new Date(2013 - 1900, 8 - 1, 21), "Primary", null),
                            new Student(null, "EB18108PR", "Elvis", "Biyogo", new Date(2005 - 1900, 10 - 1, 8), "Primary", null)
                    )
            );

            // Output the saved students to the console
            for (Student student : studentRepository.findAll()) {
                System.out.println(student);
            }
        };
    }
}
