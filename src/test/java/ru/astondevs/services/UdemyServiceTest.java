package ru.astondevs.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.astondevs.dao.UdemyDAO;
import ru.astondevs.entities.Course;
import ru.astondevs.entities.Instructor;
import ru.astondevs.entities.InstructorDetails;
import ru.astondevs.entities.Student;
import ru.astondevs.util.TestInstances;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UdemyServiceTest {
    @Mock
    UdemyDAO udemyDAO;

    @InjectMocks
    UdemyService udemyService;

    @Test
    void findAllInstructorsTest() {
        // given
        when(udemyDAO.findAllInstructors()).thenReturn(List.of(TestInstances.INSTRUCTOR));
        // when
        List<Instructor> receivedInstructors = udemyService.findAllInstructors();
        // then
        assertEquals(1, receivedInstructors.size());

        Instructor received = receivedInstructors.get(0);

        assertInstanceOf(Integer.class, received.getId());
        assertEquals("Testfirstname", received.getFirstName());
        assertEquals("Testlastname", received.getLastName());
        assertEquals(TestInstances.INSTRUCTOR_DETAILS, received.getInstructorDetails());
        assertIterableEquals(List.of(TestInstances.COURSE), received.getCourses());

        verify(udemyDAO, times(1)).findAllInstructors();
    }

    @Test
    void findAllInstructorDetailsTest() {
        // given
        when(udemyDAO.findAllInstructorDetails()).thenReturn(List.of(TestInstances.INSTRUCTOR_DETAILS));
        // when
        List<InstructorDetails> receivedDetails = udemyService.findAllInstructorDetails();
        // then
        assertEquals(1, receivedDetails.size());

        InstructorDetails received = receivedDetails.get(0);

        assertInstanceOf(Integer.class, received.getId());
        assertEquals("https://www.youtube.com/@TestChannel", received.getYoutubeChannel());
        assertEquals(TestInstances.INSTRUCTOR, received.getInstructor());

        verify(udemyDAO, times(1)).findAllInstructorDetails();
    }

    @Test
    void findAllCoursesTest() {
        // given
        when(udemyDAO.findAllCourses()).thenReturn(List.of(TestInstances.COURSE));
        // when
        List<Course> receivedCourses = udemyService.findAllCourses();
        // then
        assertEquals(1, receivedCourses.size());

        Course received = receivedCourses.get(0);

        assertInstanceOf(Integer.class, received.getId());
        assertEquals("Super test course", received.getTitle());
        assertEquals(TestInstances.INSTRUCTOR, received.getInstructor());
        assertEquals(List.of(TestInstances.STUDENT), received.getStudents());

        verify(udemyDAO, times(1)).findAllCourses();
    }

    @Test
    void findAllStudentsTest() {
        // given
        when(udemyDAO.findAllStudents()).thenReturn(List.of(TestInstances.STUDENT));
        // when
        List<Student> receivedStudents = udemyService.findAllStudents();
        // then
        assertEquals(1, receivedStudents.size());

        Student received = receivedStudents.get(0);

        assertInstanceOf(Integer.class, received.getId());
        assertEquals("Testfirstname", received.getFirstName());
        assertEquals("Testlastname", received.getLastName());
        assertEquals(List.of(TestInstances.COURSE), received.getCourses());

        verify(udemyDAO, times(1)).findAllStudents();
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(udemyDAO);
    }
}