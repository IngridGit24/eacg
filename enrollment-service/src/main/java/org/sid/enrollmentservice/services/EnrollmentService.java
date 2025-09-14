package org.sid.enrollmentservice.services;

import org.sid.enrollmentservice.entities.Application;
import org.sid.enrollmentservice.entities.IntegrationTest;

import java.util.List;

public interface EnrollmentService {
    Application createApplication(Application application);
    Application updateApplication(Long id, Application application);
    Application getApplicationById(Long id);
    Application getApplicationByNumber(String applicationNumber);
    List<Application> getAllApplications();
    List<Application> getApplicationsByStudent(Long studentId);
    List<Application> getApplicationsBySchool(Long schoolId);
    List<Application> getApplicationsByStatus(String status);
    void deleteApplication(Long id);
    
    IntegrationTest scheduleIntegrationTest(Long applicationId, IntegrationTest test);
    IntegrationTest updateIntegrationTest(Long id, IntegrationTest test);
    IntegrationTest getIntegrationTestById(Long id);
    List<IntegrationTest> getIntegrationTestsByApplication(Long applicationId);
    List<IntegrationTest> getIntegrationTestsByStudent(Long studentId);
    void deleteIntegrationTest(Long id);
    
    Application processApplication(Long applicationId, String action);
    Application approveApplication(Long applicationId);
    Application rejectApplication(Long applicationId, String reason);
}
