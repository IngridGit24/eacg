package org.sid.enrollmentservice.web;

import org.sid.enrollmentservice.entities.Application;
import org.sid.enrollmentservice.entities.IntegrationTest;
import org.sid.enrollmentservice.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@CrossOrigin(origins = "*")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/applications")
    public ResponseEntity<Application> createApplication(@RequestBody Application application) {
        Application createdApplication = enrollmentService.createApplication(application);
        return new ResponseEntity<>(createdApplication, HttpStatus.CREATED);
    }

    @GetMapping("/applications")
    public ResponseEntity<List<Application>> getAllApplications() {
        List<Application> applications = enrollmentService.getAllApplications();
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @GetMapping("/applications/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable Long id) {
        Application application = enrollmentService.getApplicationById(id);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }

    @GetMapping("/applications/number/{applicationNumber}")
    public ResponseEntity<Application> getApplicationByNumber(@PathVariable String applicationNumber) {
        Application application = enrollmentService.getApplicationByNumber(applicationNumber);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }

    @GetMapping("/applications/student/{studentId}")
    public ResponseEntity<List<Application>> getApplicationsByStudent(@PathVariable Long studentId) {
        List<Application> applications = enrollmentService.getApplicationsByStudent(studentId);
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @GetMapping("/applications/school/{schoolId}")
    public ResponseEntity<List<Application>> getApplicationsBySchool(@PathVariable Long schoolId) {
        List<Application> applications = enrollmentService.getApplicationsBySchool(schoolId);
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @GetMapping("/applications/status/{status}")
    public ResponseEntity<List<Application>> getApplicationsByStatus(@PathVariable String status) {
        List<Application> applications = enrollmentService.getApplicationsByStatus(status);
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @PutMapping("/applications/{id}")
    public ResponseEntity<Application> updateApplication(@PathVariable Long id, @RequestBody Application application) {
        Application updatedApplication = enrollmentService.updateApplication(id, application);
        return new ResponseEntity<>(updatedApplication, HttpStatus.OK);
    }

    @PostMapping("/applications/{id}/process")
    public ResponseEntity<Application> processApplication(@PathVariable Long id, @RequestParam String action) {
        Application application = enrollmentService.processApplication(id, action);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }

    @PostMapping("/applications/{id}/approve")
    public ResponseEntity<Application> approveApplication(@PathVariable Long id) {
        Application application = enrollmentService.approveApplication(id);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }

    @PostMapping("/applications/{id}/reject")
    public ResponseEntity<Application> rejectApplication(@PathVariable Long id, @RequestParam String reason) {
        Application application = enrollmentService.rejectApplication(id, reason);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }

    @DeleteMapping("/applications/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        enrollmentService.deleteApplication(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Integration Test endpoints
    @PostMapping("/integration-tests")
    public ResponseEntity<IntegrationTest> scheduleIntegrationTest(@RequestBody IntegrationTest test) {
        IntegrationTest scheduledTest = enrollmentService.scheduleIntegrationTest(test.getApplicationId(), test);
        return new ResponseEntity<>(scheduledTest, HttpStatus.CREATED);
    }

    @GetMapping("/integration-tests/{id}")
    public ResponseEntity<IntegrationTest> getIntegrationTestById(@PathVariable Long id) {
        IntegrationTest test = enrollmentService.getIntegrationTestById(id);
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @GetMapping("/integration-tests/application/{applicationId}")
    public ResponseEntity<List<IntegrationTest>> getIntegrationTestsByApplication(@PathVariable Long applicationId) {
        List<IntegrationTest> tests = enrollmentService.getIntegrationTestsByApplication(applicationId);
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @GetMapping("/integration-tests/student/{studentId}")
    public ResponseEntity<List<IntegrationTest>> getIntegrationTestsByStudent(@PathVariable Long studentId) {
        List<IntegrationTest> tests = enrollmentService.getIntegrationTestsByStudent(studentId);
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @PutMapping("/integration-tests/{id}")
    public ResponseEntity<IntegrationTest> updateIntegrationTest(@PathVariable Long id, @RequestBody IntegrationTest test) {
        IntegrationTest updatedTest = enrollmentService.updateIntegrationTest(id, test);
        return new ResponseEntity<>(updatedTest, HttpStatus.OK);
    }

    @DeleteMapping("/integration-tests/{id}")
    public ResponseEntity<Void> deleteIntegrationTest(@PathVariable Long id) {
        enrollmentService.deleteIntegrationTest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
