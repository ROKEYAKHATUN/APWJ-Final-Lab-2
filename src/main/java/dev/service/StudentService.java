package dev.service;

import dev.domain.Student;
import dev.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createStudent(Student student) throws SQLException {
        studentRepository.createStudent(student);
    }

    public List<Student> getAllStudents() throws SQLException {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) throws SQLException {
        return studentRepository.findById(id);
    }

    public void updateStudentById(Student updatedStudent) throws SQLException {
        studentRepository.updateStudentById(updatedStudent);
    }

    public void deleteStudentById(int id) throws SQLException {
        studentRepository.deleteById(id);
    }

    public int getStudentCount() {
        return studentRepository.getStudentCount();
    }

}
