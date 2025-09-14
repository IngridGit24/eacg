package org.sid.enrollmentservice.entities;

public enum ApplicationStatus {
    PENDING,
    UNDER_REVIEW,
    DOCUMENTS_REQUIRED,
    INTEGRATION_TEST_SCHEDULED,
    INTEGRATION_TEST_COMPLETED,
    APPROVED,
    REJECTED,
    ENROLLED
}
