package org.sid.enrollmentservice.services;

import org.sid.enrollmentservice.entities.*;
import org.sid.enrollmentservice.repositories.ApplicationRepository;
import org.sid.enrollmentservice.repositories.IntegrationTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private ApplicationRepository applicationRepository;
    
    @Autowired
    private IntegrationTestRepository integrationTestRepository;

    @Override
    public Application createApplication(Application application) {
        application.setApplicationNumber(generateApplicationNumber());
        application.setStatus(ApplicationStatus.PENDING);
        return applicationRepository.save(application);
    }

    @Override
    public Application updateApplication(Long id, Application application) {
        Application existingApplication = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        
        existingApplication.setSchoolId(application.getSchoolId());
        existingApplication.setRequestedLevel(application.getRequestedLevel());
        existingApplication.setAcademicYear(application.getAcademicYear());
        existingApplication.setNotes(application.getNotes());
        existingApplication.setDocuments(application.getDocuments());
        
        return applicationRepository.save(existingApplication);
    }

    @Override
    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
    }

    @Override
    public Application getApplicationByNumber(String applicationNumber) {
        return applicationRepository.findByApplicationNumber(applicationNumber)
                .orElseThrow(() -> new RuntimeException("Application not found"));
    }

    @Override
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    @Override
    public List<Application> getApplicationsByStudent(Long studentId) {
        return applicationRepository.findByStudentId(studentId);
    }

    @Override
    public List<Application> getApplicationsBySchool(Long schoolId) {
        return applicationRepository.findBySchoolId(schoolId);
    }

    @Override
    public List<Application> getApplicationsByStatus(String status) {
        return applicationRepository.findByStatus(ApplicationStatus.valueOf(status.toUpperCase()));
    }

    @Override
    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    @Override
    public IntegrationTest scheduleIntegrationTest(Long applicationId, IntegrationTest test) {
        Application application = getApplicationById(applicationId);
        application.setStatus(ApplicationStatus.INTEGRATION_TEST_SCHEDULED);
        applicationRepository.save(application);
        
        test.setApplicationId(applicationId);
        test.setStudentId(application.getStudentId());
        test.setStatus(TestStatus.SCHEDULED);
        
        return integrationTestRepository.save(test);
    }

    @Override
    public IntegrationTest updateIntegrationTest(Long id, IntegrationTest test) {
        IntegrationTest existingTest = integrationTestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Integration test not found"));
        
        existingTest.setTestDate(test.getTestDate());
        existingTest.setTestLocation(test.getTestLocation());
        existingTest.setScore(test.getScore());
        existingTest.setComments(test.getComments());
        existingTest.setExaminerName(test.getExaminerName());
        existingTest.setStatus(test.getStatus());
        
        return integrationTestRepository.save(existingTest);
    }

    @Override
    public IntegrationTest getIntegrationTestById(Long id) {
        return integrationTestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Integration test not found"));
    }

    @Override
    public List<IntegrationTest> getIntegrationTestsByApplication(Long applicationId) {
        return integrationTestRepository.findByApplicationId(applicationId);
    }

    @Override
    public List<IntegrationTest> getIntegrationTestsByStudent(Long studentId) {
        return integrationTestRepository.findByStudentId(studentId);
    }

    @Override
    public void deleteIntegrationTest(Long id) {
        integrationTestRepository.deleteById(id);
    }

    @Override
    public Application processApplication(Long applicationId, String action) {
        Application application = getApplicationById(applicationId);
        
        switch (action.toLowerCase()) {
            case "approve":
                return approveApplication(applicationId);
            case "reject":
                return rejectApplication(applicationId, "Application rejected");
            case "schedule_test":
                application.setStatus(ApplicationStatus.INTEGRATION_TEST_SCHEDULED);
                break;
            case "review":
                application.setStatus(ApplicationStatus.UNDER_REVIEW);
                break;
            default:
                throw new RuntimeException("Invalid action: " + action);
        }
        
        return applicationRepository.save(application);
    }

    @Override
    public Application approveApplication(Long applicationId) {
        Application application = getApplicationById(applicationId);
        application.setStatus(ApplicationStatus.APPROVED);
        return applicationRepository.save(application);
    }

    @Override
    public Application rejectApplication(Long applicationId, String reason) {
        Application application = getApplicationById(applicationId);
        application.setStatus(ApplicationStatus.REJECTED);
        application.setNotes(application.getNotes() + "\nRejection reason: " + reason);
        return applicationRepository.save(application);
    }

    private String generateApplicationNumber() {
        return "APP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
