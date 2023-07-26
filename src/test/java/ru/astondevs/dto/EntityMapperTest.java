package ru.astondevs.dto;

import org.junit.jupiter.api.Test;
import ru.astondevs.util.TestInstances;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntityMapperTest {
    EntityMapper entityMapper;

    public EntityMapperTest() {
        entityMapper = new EntityMapper();
    }

    @Test
    void mapInstructorToDTOTest() {
        // given, when
        InstructorDTO mapped = entityMapper.mapInstructorToDTO(TestInstances.INSTRUCTOR);
        // then
        assertEquals(TestInstances.INSTRUCTOR.getId(), mapped.getId());
        assertEquals("Testfirstname Testlastname", mapped.getFullName());
        assertEquals("https://www.youtube.com/@TestChannel", mapped.getYoutubeChannel());
        assertEquals(List.of("Super test course"), mapped.getCourses());
    }

    @Test
    void mapInstructorDetailsToDTOTest() {
        // given, when]
        InstructorDetailsDTO mapped = entityMapper
                .mapInstructorDetailsToDTO(TestInstances.INSTRUCTOR_DETAILS);
        // then
        assertEquals(TestInstances.INSTRUCTOR_DETAILS.getId(), mapped.getId());
        assertEquals("https://www.youtube.com/@TestChannel", mapped.getYoutubeChannel());
        assertEquals("Testfirstname Testlastname", mapped.getInstructor());
    }

    @Test
    void mapCourseToDTOTest() {
        // given, when
        CourseDTO mapped = entityMapper.mapCourseToDTO(TestInstances.COURSE);
        // then
        assertEquals(TestInstances.COURSE.getId(), mapped.getId());
        assertEquals("Super test course", mapped.getTitle());
        assertEquals("Testfirstname Testlastname", mapped.getInstructor());
        assertEquals(List.of("Testfirstname Testlastname"), mapped.getStudents());

    }

    @Test
    void mapStudentToDTOTest() {
        // given, when
        StudentDTO mapped = entityMapper.mapStudentToDTO(TestInstances.STUDENT);
        // then
        assertEquals(TestInstances.STUDENT.getId(), mapped.getId());
        assertEquals("Testfirstname Testlastname", mapped.getFullName());
        assertEquals(List.of("Super test course"), mapped.getCourses());
    }
}