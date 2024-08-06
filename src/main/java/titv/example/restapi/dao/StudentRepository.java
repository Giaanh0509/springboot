package titv.example.restapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import titv.example.restapi.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("SELECT s FROM Student s WHERE s.firstName <> ?1")
    public List<Student> findByFirstNameNot(String firstName);

    public Student findById(int id);
}
