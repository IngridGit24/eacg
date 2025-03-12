package org.sid.schoolservice.repositories;

import org.sid.schoolservice.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SchoolRepo extends JpaRepository<School, Long> {

}
