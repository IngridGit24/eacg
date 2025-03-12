package org.sid.schoolservice;

import org.sid.schoolservice.entities.School;
import org.sid.schoolservice.entities.Type;
import org.sid.schoolservice.repositories.SchoolRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SchoolServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner start(SchoolRepo schoolRepo, RepositoryRestConfiguration restConfiguration) {
		// Expose entity IDs in REST API responses
		restConfiguration.exposeIdsFor(School.class);

		return args -> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			List<School> schools = List.of(
					new School(null, "SCH001", "École Primaire Akanda", sdf.parse("1998-09-15"), Type.Elementary, "Libreville, Gabon", 12, 25),
					new School(null, "SCH002", "Collège Jean Ping", sdf.parse("2005-03-20"), Type.Middle_school, "Port-Gentil, Gabon", 18, 30),
					new School(null, "SCH003", "Lycée National Léon Mba", sdf.parse("1972-06-10"), Type.High_school, "Libreville, Gabon", 25, 40),
					new School(null, "SCH004", "Université Omar Bongo", sdf.parse("1970-01-01"), Type.College, "Libreville, Gabon", 35, 50),
					new School(null, "SCH005", "Institut Polytechnique de Franceville", sdf.parse("1990-11-05"), Type.College, "Franceville, Gabon", 20, 45)
			);

			// Save school to the database
			schoolRepo.saveAll(schools);

			// Output the saved schools to the console
			schoolRepo.findAll().forEach(System.out::println);
		};
	}
}
