package org.sid.enrollmentservice.repositories;

import org.sid.enrollmentservice.entities.IntegrationTest;
import org.sid.enrollmentservice.entities.TestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IntegrationTestRepository extends JpaRepository<IntegrationTest, Long> {
    List<IntegrationTest> findByApplicationId(Long applicationId);
    List<IntegrationTest> findByStudentId(Long studentId);
    List<IntegrationTest> findByStatus(TestStatus status);
    List<IntegrationTest> findByTestDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
