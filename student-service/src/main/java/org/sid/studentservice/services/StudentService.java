package org.sid.studentservice.services;

import org.sid.studentservice.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student registerStudent(Student student);

    Student updateStudent(Long id, Student student);

    void deleteStudent(Long id);

    List<Student> getAllStudents();


    Optional<Student> getStudentById(Long id);

    Optional<Student> getStudentByMatricule(String matricule);

    List<Student> getStudentsByLevel(String level);

    boolean checkStudent(String matricule);

    List<Student> searchStudentsByName(String name);

}
