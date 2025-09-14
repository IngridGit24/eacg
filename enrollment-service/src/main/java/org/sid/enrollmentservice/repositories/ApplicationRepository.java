package org.sid.enrollmentservice.repositories;

import org.sid.enrollmentservice.entities.Application;
import org.sid.enrollmentservice.entities.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findByApplicationNumber(String applicationNumber);
    List<Application> findByStudentId(Long studentId);
    List<Application> findBySchoolId(Long schoolId);
    List<Application> findByStatus(ApplicationStatus status);
    List<Application> findByAcademicYear(String academicYear);
    List<Application> findBySchoolIdAndStatus(Long schoolId, ApplicationStatus status);
}
