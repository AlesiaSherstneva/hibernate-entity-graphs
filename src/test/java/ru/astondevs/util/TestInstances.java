package ru.astondevs.util;

import ru.astondevs.entities.Course;
import ru.astondevs.entities.Instructor;
import ru.astondevs.entities.InstructorDetails;
import ru.astondevs.entities.Student;

import java.util.List;
import java.util.Random;

public class TestInstances {
    public static final Instructor INSTRUCTOR;
    public static final InstructorDetails INSTRUCTOR_DETAILS;
    public static final Course COURSE;
    public static final Student STUDENT;

    static {
        Random random = new Random();

        INSTRUCTOR = new Instructor();
        INSTRUCTOR.setId(random.nextInt(100));
        INSTRUCTOR.setFirstName("Testfirstname");
        INSTRUCTOR.setLastName("Testlastname");

        INSTRUCTOR_DETAILS = new InstructorDetails();
        INSTRUCTOR_DETAILS.setId(random.nextInt(100));
        INSTRUCTOR_DETAILS.setYoutubeChannel("https://www.youtube.com/@TestChannel");

        COURSE = new Course();
        COURSE.setId(random.nextInt(100));
        COURSE.setTitle("Super test course");

        STUDENT = new Student();
        STUDENT.setId(random.nextInt(100));
        STUDENT.setFirstName("Testfirstname");
        STUDENT.setLastName("Testlastname");

        INSTRUCTOR.setInstructorDetails(INSTRUCTOR_DETAILS);
        INSTRUCTOR.setCourses(List.of(COURSE));

        INSTRUCTOR_DETAILS.setInstructor(INSTRUCTOR);

        COURSE.setInstructor(INSTRUCTOR);
        COURSE.setStudents(List.of(STUDENT));

        STUDENT.setCourses(List.of(COURSE));
    }
}
