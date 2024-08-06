package titv.example.restapi.service;
import titv.example.restapi.entity.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    public List<Student> getAll();

    public Student getByID(int id);

    public Student addStudent(Student student);

    public Student updateStudent(Student student);

    public void deleteById(int id);

    List<Student> getAllStudentNotFirstName(String firstName);
}
