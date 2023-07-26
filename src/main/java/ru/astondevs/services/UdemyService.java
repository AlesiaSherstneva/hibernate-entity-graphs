package ru.astondevs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.astondevs.dao.UdemyDAO;
import ru.astondevs.entities.Course;
import ru.astondevs.entities.Instructor;
import ru.astondevs.entities.InstructorDetails;
import ru.astondevs.entities.Student;

import java.util.List;

@Service
public class UdemyService {
    private final UdemyDAO udemyDAO;

    @Autowired
    public UdemyService(UdemyDAO udemyDAO) {
        this.udemyDAO = udemyDAO;
    }

    public List<Instructor> findAllInstructors() {
        return udemyDAO.findAllInstructors();
    }

    public List<InstructorDetails> findAllInstructorDetails() {
        return udemyDAO.findAllInstructorDetails();
    }

    public List<Course> findAllCourses() {
        return udemyDAO.findAllCourses();
    }

    public List<Student> findAllStudents() {
        return udemyDAO.findAllStudents();
    }
}