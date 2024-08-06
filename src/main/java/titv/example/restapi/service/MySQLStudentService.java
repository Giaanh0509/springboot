package titv.example.restapi.service;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import titv.example.restapi.dao.StudentRepository;
import titv.example.restapi.entity.Student;

import java.util.List;

@Service
@Transactional
public class MySQLStudentService implements StudentService{
//    private StudentDAO studentDAO;

    private StudentRepository studentRepository;

    @Autowired
    public MySQLStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student getByID(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.saveAndFlush(student);
    }

    @Override
    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAllStudentNotFirstName(String firstName) {
        return studentRepository.findByFirstNameNot(firstName);
    }
}
