package org.sid.enrollmentservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Application {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String applicationNumber;
    private Long studentId;
    private Long schoolId;
    private LocalDateTime applicationDate;
    
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    
    @Enumerated(EnumType.STRING)
    private SchoolLevel requestedLevel;
    
    private String academicYear;
    private String notes;
    
    @ElementCollection
    @CollectionTable(name = "application_documents", joinColumns = @JoinColumn(name = "application_id"))
    @Column(name = "document_url")
    private List<String> documents;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
