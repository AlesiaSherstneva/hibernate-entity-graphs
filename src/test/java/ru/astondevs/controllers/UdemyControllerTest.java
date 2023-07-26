package ru.astondevs.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.astondevs.dto.EntityMapper;
import ru.astondevs.services.UdemyService;
import ru.astondevs.util.TestInstances;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UdemyControllerTest {
    UdemyController udemyController;
    EntityMapper entityMapper;

    @Mock
    UdemyService udemyService;

    MockMvc mockMvc;

    public UdemyControllerTest() {
        entityMapper = new EntityMapper();
    }

    @BeforeEach
    void setUp() {
        udemyController = new UdemyController(udemyService, entityMapper);
        mockMvc = MockMvcBuilders.standaloneSetup(udemyController).build();
    }

    @Test
    void getInstructorsTest() throws Exception {
        // given
        when(udemyService.findAllInstructors()).thenReturn(List.of(TestInstances.INSTRUCTOR));
        // when, then
        mockMvc.perform(get("/instructors"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$", hasSize(1)),
                        jsonPath("$[0].id", is(TestInstances.INSTRUCTOR.getId())),
                        jsonPath("$[0].fullName", is("Testfirstname Testlastname")),
                        jsonPath("$[0].youtubeChannel", is("https://www.youtube.com/@TestChannel")),
                        jsonPath("$[0].courses", hasSize(1)),
                        jsonPath("$[0].courses[0]", is("Super test course"))
                );

        verify(udemyService, times(1)).findAllInstructors();
    }

    @Test
    void getInstructorDetailsTest() throws Exception {
        // given
        when(udemyService.findAllInstructorDetails()).thenReturn(List.of(TestInstances.INSTRUCTOR_DETAILS));
        // when, then
        mockMvc.perform(get("/instructor-details"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$", hasSize(1)),
                        jsonPath("$[0].id", is(TestInstances.INSTRUCTOR_DETAILS.getId())),
                        jsonPath("$[0].youtubeChannel", is("https://www.youtube.com/@TestChannel")),
                        jsonPath("$[0].instructor", is("Testfirstname Testlastname"))
                );

        verify(udemyService, times(1)).findAllInstructorDetails();
    }

    @Test
    void getCoursesTest() throws Exception {
        // given
        when(udemyService.findAllCourses()).thenReturn(List.of(TestInstances.COURSE));
        // when, then
        mockMvc.perform(get("/courses"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$", hasSize(1)),
                        jsonPath("$[0].id", is(TestInstances.COURSE.getId())),
                        jsonPath("$[0].title", is("Super test course")),
                        jsonPath("$[0].instructor", is("Testfirstname Testlastname")),
                        jsonPath("$[0].students", hasSize(1)),
                        jsonPath("$[0].students[0]", is("Testfirstname Testlastname"))
                );

        verify(udemyService, times(1)).findAllCourses();
    }

    @Test
    void getStudents() throws Exception {
        // given
        when(udemyService.findAllStudents()).thenReturn(List.of(TestInstances.STUDENT));
        // when, then
        mockMvc.perform(get("/students"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$", hasSize(1)),
                        jsonPath("$[0].id", is(TestInstances.STUDENT.getId())),
                        jsonPath("$[0].fullName", is("Testfirstname Testlastname")),
                        jsonPath("$[0].courses", hasSize(1)),
                        jsonPath("$[0].courses[0]", is("Super test course"))
                );

        verify(udemyService, times(1)).findAllStudents();
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(udemyService);
    }
}