package ru.astondevs.dao;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.astondevs.entities.Course;
import ru.astondevs.entities.Instructor;
import ru.astondevs.entities.InstructorDetails;
import ru.astondevs.entities.Student;

import java.util.List;

@Repository
public class UdemyDAOImpl implements UdemyDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public UdemyDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Instructor> findAllInstructors() {
        List<Instructor> foundInstructors;

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Instructor> criteria = builder.createQuery(Instructor.class);
        Root<Instructor> instructor = criteria.from(Instructor.class);
        criteria.select(instructor);

        EntityGraph<Instructor> instructorEntityGraph = session.createEntityGraph(Instructor.class);
        instructorEntityGraph.addAttributeNodes("instructorDetails", "courses");

        foundInstructors = session.createQuery(criteria)
                .setHint("jakarta.persistence.fetchgraph", instructorEntityGraph)
                .getResultList();

        session.getTransaction().commit();
        session.close();

        return foundInstructors;
    }

    @Override
    public List<InstructorDetails> findAllInstructorDetails() {
        List<InstructorDetails> foundInstructorDetails;

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<InstructorDetails> criteria = builder.createQuery(InstructorDetails.class);
        Root<InstructorDetails> allInstructorDetails = criteria.from(InstructorDetails.class);
        criteria.select(allInstructorDetails);

        foundInstructorDetails = session.createQuery(criteria).getResultList();

        session.getTransaction().commit();
        session.close();

        return foundInstructorDetails;
    }

    @Override
    public List<Course> findAllCourses() {
        List<Course> foundCourses;

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> criteria = builder.createQuery(Course.class);
        Root<Course> allCourses = criteria.from(Course.class);
        criteria.select(allCourses);
        foundCourses = session.createQuery(criteria)
                .setHint("jakarta.persistence.fetchgraph",
                        sessionFactory.findEntityGraphByName("course-entity-graph"))
                .getResultList();

        session.getTransaction().commit();
        session.close();

        return foundCourses;
    }

    @Override
    public List<Student> findAllStudents() {
        List<Student> foundStudents;

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> criteria = builder.createQuery(Student.class);
        Root<Student> allStudents = criteria.from(Student.class);
        criteria.select(allStudents);
        foundStudents = session.createQuery(criteria)
                .setHint("jakarta.persistence.fetchgraph",
                        sessionFactory.findEntityGraphByName("student-entity-graph"))
                .getResultList();

        session.getTransaction().commit();
        session.close();

        return foundStudents;
    }
}