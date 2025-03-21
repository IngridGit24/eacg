package org.sid.studentservice.repositories;

import org.sid.studentservice.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StudentRepo extends JpaRepository<Student, Long> {

}
