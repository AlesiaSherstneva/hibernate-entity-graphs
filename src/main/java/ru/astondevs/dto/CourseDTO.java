package ru.astondevs.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CourseDTO {
    private int id;
    private String title;
    private String instructor;
    private List<String> students;
}