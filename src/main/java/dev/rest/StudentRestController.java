package dev.rest;

import dev.domain.Student;
import dev.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class StudentRestController {

    private final StudentService studentService;

    public StudentRestController(StudentService StudentService) {
        this.studentService = StudentService;
    }

    @PostMapping("/users")
    public String createStudent(@RequestBody Student student) throws SQLException {
        studentService.createStudent(student);
        return "User Created Successfully!";
    }

    @GetMapping("/users/count")
    public String getStudentCount() {
        int studentCount = studentService.getStudentCount();
        return "Total Number of Students: " + studentCount;
    }

    @GetMapping("/users/{id}")
    public Student findStudent(@PathVariable("id") Integer id) throws SQLException {
        return studentService.getStudentById(id);
    }

    @PutMapping("/users")
    public String editStudent(@RequestBody Student student) throws SQLException {
        studentService.updateStudentById(student);
        return "User Updated Successfully!";
    }

    @GetMapping("/users")
    public List<Student> getAllStudents() throws SQLException {
        return studentService.getAllStudents();
    }

    @DeleteMapping("/users/{id}")
    public String deleteStudent(@PathVariable int id) throws SQLException {
        studentService.deleteStudentById(id);
        return "Deleted Successfully!";
    }

}
