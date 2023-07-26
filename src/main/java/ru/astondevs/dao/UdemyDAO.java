package ru.astondevs.dao;

import ru.astondevs.entities.Course;
import ru.astondevs.entities.Instructor;
import ru.astondevs.entities.InstructorDetails;
import ru.astondevs.entities.Student;

import java.util.List;

public interface UdemyDAO {
    List<Instructor> findAllInstructors();

    List<InstructorDetails> findAllInstructorDetails();

    List<Course> findAllCourses();

    List<Student> findAllStudents();
}