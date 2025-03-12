package org.sid.studentservice.services;

import org.sid.studentservice.entities.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Override
    public Student registerStudent(Student student) {
        return null;
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        return null;
    }

    @Override
    public void deleteStudent(Long id) {

    }

    @Override
    public List<Student> getAllStudents() {
        return List.of();
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Student> getStudentByMatricule(String matricule) {
        return Optional.empty();
    }

    @Override
    public List<Student> getStudentsByLevel(String level) {
        return List.of();
    }

    @Override
    public boolean checkStudent(String matricule) {
        return false;
    }

    @Override
    public List<Student> searchStudentsByName(String name) {
        return List.of();
    }
}
