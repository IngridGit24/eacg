package org.sid.schoolservice.services;

import org.sid.schoolservice.entities.School;
import org.sid.schoolservice.entities.Type;
import org.sid.schoolservice.repositories.SchoolRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepo schoolRepo;

    @Autowired
    public SchoolServiceImpl(SchoolRepo schoolRepo) {
        this.schoolRepo = schoolRepo;
    }

    @Override
    public School createSchool(School school) {
        return schoolRepo.save(school);
    }

    @Override
    public School updateSchool(Long id, School school) {
        School existingSchool = schoolRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("School doesn't exist with id = " + id));

        existingSchool.setMatricule(school.getMatricule());
        existingSchool.setName(school.getName());
        existingSchool.setDateOfCreation(school.getDateOfCreation());
        existingSchool.setTypeOfSchool(school.getTypeOfSchool());
        existingSchool.setLocation(school.getLocation());
        existingSchool.setNumberOfClass(school.getNumberOfClass());
        existingSchool.setCapacityPerClass(school.getCapacityPerClass());

        return schoolRepo.save(existingSchool);
    }

    @Override
    public List<School> getAllSchool() {
        return schoolRepo.findAll();
    }

    @Override
    public List<School> getSchoolByType(Type typeOfSchool) {
        return schoolRepo.findByTypeOfSchool(typeOfSchool);
    }

    @Override
    public void deleteSchool(Long id) {
        if (!schoolRepo.existsById(id)) {
            throw new RuntimeException("School with id " + id + " does not exist.");
        }
        schoolRepo.deleteById(id);
    }
}
