package org.sid.schoolservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class School {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matricule;

    private String name;
    private Date dateOfCreation;
    @Enumerated(EnumType.STRING)
    private Type typeOfSchool;
    private String location;
    private Integer numberOfClass;
    private Integer capacityPerClass;
}
