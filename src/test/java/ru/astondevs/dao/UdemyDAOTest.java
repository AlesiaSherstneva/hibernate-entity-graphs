package ru.astondevs.dao;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.astondevs.config.TestConfig;
import ru.astondevs.entities.Course;
import ru.astondevs.entities.Instructor;
import ru.astondevs.entities.InstructorDetails;
import ru.astondevs.entities.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class UdemyDAOTest {
    @Autowired
    SessionFactory sessionFactory;

    UdemyDAO udemyDAO;

    @BeforeEach
    void setUp() {
        udemyDAO = new UdemyDAOImpl(sessionFactory);
    }

    @Test
    void findAllInstructorsTest() {
        // given, when
        List<Instructor> receivedInstructors = udemyDAO.findAllInstructors();
        // then
        assertEquals(3, receivedInstructors.size());
        assertEquals("First", receivedInstructors.get(0).getFirstName());
        assertEquals("https://www.youtube.com/@second",
                receivedInstructors.get(1).getInstructorDetails().getYoutubeChannel());
        assertEquals(1, receivedInstructors.get(2).getCourses().size());
    }

    @Test
    void findAllInstructorDetailsTest() {
        // given, when
        List<InstructorDetails> receivedDetails = udemyDAO.findAllInstructorDetails();
        // then
        assertEquals(3, receivedDetails.size());
        assertEquals("https://www.youtube.com/@first", receivedDetails.get(1).getYoutubeChannel());
        assertEquals("Third", receivedDetails.get(0).getInstructor().getFirstName());
        for (InstructorDetails details : receivedDetails) {
            assertEquals("Instructor", details.getInstructor().getLastName());
        }
    }

    @Test
    void findAllCoursesTest() {
        // given, when
        List<Course> receivedCourses = udemyDAO.findAllCourses();
        // then
        assertEquals(6, receivedCourses.size());
        assertEquals("Pretty Java Advanced course", receivedCourses.get(0).getTitle());
        assertEquals("Full Stack developer course",
                receivedCourses.get(receivedCourses.size() - 1).getTitle());
        assertEquals("First", receivedCourses.get(1).getInstructor().getFirstName());
        assertEquals(2, receivedCourses.get(1).getStudents().size());
        assertEquals("Second", receivedCourses.get(5).getStudents().get(0).getFirstName());
    }

    @Test
    void findAllStudentsTest() {
        // given, when
        List<Student> receivedStudents = udemyDAO.findAllStudents();
        // then
        assertEquals(3, receivedStudents.size());
        assertEquals("First", receivedStudents.get(0).getFirstName());
        for (Student student : receivedStudents) {
            assertEquals("Student", student.getLastName());
        }
        assertEquals(3, receivedStudents.get(0).getCourses().size());
        assertEquals(0, receivedStudents.get(2).getCourses().size());
    }
}