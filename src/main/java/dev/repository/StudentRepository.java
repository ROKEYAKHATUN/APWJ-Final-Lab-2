package dev.repository;

import dev.domain.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentRepository {

    private final SessionFactory sessionFactory;

    public StudentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query<?> query = session.createSQLQuery("select 1 from students");
            query.setMaxResults(1);
            query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            createStudentsTable(session);
        } finally {
            session.close();
        }
    }

    private void createStudentsTable(@org.jetbrains.annotations.NotNull Session session) {
        session.getSessionFactory().openStatelessSession().close();
    }

    public void createStudent(Student student) throws SQLException {

        Session session = sessionFactory.getCurrentSession();
        session.save(student);

    }

    public List<Student> findAll() throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Query<Student> userQuery = session.createQuery("from Student", Student.class);
        return userQuery.getResultList();
    }

    public Student findById(int id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Student.class, id);
    }

    public void updateStudentById(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.update(student);
    }

    public void deleteById(int id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Student student = findById(id);
        session.delete(student);
    }


    public int getStudentCount() {
        Session session = sessionFactory.getCurrentSession();
        Long count = (Long) session.createQuery("SELECT COUNT(*) FROM Student").uniqueResult();
        return Math.toIntExact(count != null ? count : 0);
    }

}
