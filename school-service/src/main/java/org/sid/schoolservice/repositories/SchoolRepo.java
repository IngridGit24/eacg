package org.sid.schoolservice.repositories;

import org.sid.schoolservice.entities.School;
import org.sid.schoolservice.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface SchoolRepo extends JpaRepository<School, Long> {

    List<School> findByTypeOfSchool(Type typeOfSchool);
}
