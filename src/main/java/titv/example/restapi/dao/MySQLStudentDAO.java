package titv.example.restapi.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Repository;
import titv.example.restapi.entity.Student;

import java.util.List;

@Repository
@Transactional
public class MySQLStudentDAO implements StudentDAO {

    private EntityManager entityManager;

    @Autowired
    public MySQLStudentDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Student> getAll() {
        String jpql = "Select s from Student s";
        return entityManager.createQuery(jpql, Student.class).getResultList();
    }

    @Override
    public Student getByID(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public Student addStudent(Student student) {
        entityManager.persist(student);
        return student;
    }

    @Override
    public Student updateStudent(Student student) {
        entityManager.merge(student);
        entityManager.flush();
        return student;
    }

    @Override
    public void deleteById(int id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }
}
