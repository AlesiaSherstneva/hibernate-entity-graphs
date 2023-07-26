package ru.astondevs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.astondevs.dto.*;
import ru.astondevs.entities.Course;
import ru.astondevs.entities.Instructor;
import ru.astondevs.entities.InstructorDetails;
import ru.astondevs.entities.Student;
import ru.astondevs.services.UdemyService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UdemyController {
    private final UdemyService udemyService;
    private final EntityMapper entityMapper;

    @Autowired
    public UdemyController(UdemyService udemyService, EntityMapper entityMapper) {
        this.udemyService = udemyService;
        this.entityMapper = entityMapper;
    }

    @GetMapping("/instructors")
    public List<InstructorDTO> getInstructors() {
        List<InstructorDTO> instructorDTOs = new ArrayList<>();
        for (Instructor instructor: udemyService.findAllInstructors()) {
            instructorDTOs.add(entityMapper.mapInstructorToDTO(instructor));
        }
        return instructorDTOs;
    }

    @GetMapping("/instructor-details")
    public List<InstructorDetailsDTO> getInstructorDetails() {
        List<InstructorDetailsDTO> detailsDTOs = new ArrayList<>();
        for (InstructorDetails details: udemyService.findAllInstructorDetails()) {
            detailsDTOs.add(entityMapper.mapInstructorDetailsToDTO(details));
        }
        return detailsDTOs;
    }

    @GetMapping("/courses")
    public List<CourseDTO> getCourses() {
        List<CourseDTO> courseDTOs = new ArrayList<>();
        for (Course course: udemyService.findAllCourses()) {
            courseDTOs.add(entityMapper.mapCourseToDTO(course));
        }
        return courseDTOs;
    }

    @GetMapping("/students")
    public List<StudentDTO> getStudents() {
        List<StudentDTO> studentDTOs = new ArrayList<>();
        for(Student student: udemyService.findAllStudents()) {
            studentDTOs.add(entityMapper.mapStudentToDTO(student));
        }
        return studentDTOs;
    }
}