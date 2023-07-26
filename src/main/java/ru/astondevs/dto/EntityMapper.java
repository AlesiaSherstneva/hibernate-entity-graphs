package ru.astondevs.dto;

import org.springframework.stereotype.Component;
import ru.astondevs.entities.Course;
import ru.astondevs.entities.Instructor;
import ru.astondevs.entities.InstructorDetails;
import ru.astondevs.entities.Student;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityMapper {
    public InstructorDTO mapInstructorToDTO(Instructor instructor) {
        List<String> courses = new ArrayList<>();
        for (Course course: instructor.getCourses()) {
            courses.add(course.getTitle());
        }

        return InstructorDTO.builder()
                .id(instructor.getId())
                .fullName(String.format("%s %s", instructor.getFirstName(), instructor.getLastName()))
                .youtubeChannel(instructor.getInstructorDetails().getYoutubeChannel())
                .courses(courses)
                .build();
    }

    public InstructorDetailsDTO mapInstructorDetailsToDTO(InstructorDetails details) {
        return InstructorDetailsDTO.builder()
                .id(details.getId())
                .instructor(String.format("%s %s",
                        details.getInstructor().getFirstName(), details.getInstructor().getLastName()))
                .youtubeChannel(details.getYoutubeChannel())
                .build();
    }

    public CourseDTO mapCourseToDTO(Course course) {
        List<String> students = new ArrayList<>();
        for (Student student: course.getStudents()) {
            students.add(String.format("%s %s", student.getFirstName(), student.getLastName()));
        }

        return CourseDTO.builder()
                .id(course.getId())
                .title(course.getTitle())
                .instructor(String.format("%s %s",
                        course.getInstructor().getFirstName(), course.getInstructor().getLastName()))
                .students(students)
                .build();
    }

    public StudentDTO mapStudentToDTO(Student student) {
        List<String> courses = new ArrayList<>();
        for (Course course: student.getCourses()) {
            courses.add(course.getTitle());
        }

        return StudentDTO.builder()
                .id(student.getId())
                .fullName(String.format("%s %s", student.getFirstName(), student.getLastName()))
                .courses(courses)
                .build();
    }
}