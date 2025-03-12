package org.sid.schoolservice.services;

import org.sid.schoolservice.entities.School;
import org.sid.schoolservice.entities.Type;

import java.util.List;

public interface SchoolService {

   School createSchool(School school);

   School updateSchool(Long id, School school);

   List<School> getAllSchool();

   List<School> getSchoolByType(Type typeOfSchool);

   void deleteSchool(Long id);

}
