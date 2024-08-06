package titv.example.restapi.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import titv.example.restapi.dao.StudentDAO;
import titv.example.restapi.entity.Student;
import titv.example.restapi.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired


    @GetMapping
    public List<Student> getAllStudents() {
        return this.studentService.getAll();
    }

    @GetMapping("/not-find/{name}")
    public List<Student> getAllStudentsNotFirstName(@PathVariable String name) {
        return this.studentService.getAllStudentNotFirstName(name);
    }

    @GetMapping("/id={id}")
    public ResponseEntity<Student> getById(@PathVariable int id) {
        Student student = studentService.getByID(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        student = studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student) {
        Student existStudent = studentService.getByID(id);
        if (existStudent != null) {
            existStudent.setFirstName(student.getFirstName());
            existStudent.setLastName(student.getLastName());
            existStudent.setEmail(student.getEmail());
            student = studentService.updateStudent(existStudent);
            return ResponseEntity.ok(existStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id, @RequestBody Student student) {
        Student existStudent = studentService.getByID(id);
        if (existStudent != null) {
            studentService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
