package ru.astondevs.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class StudentDTO {
    private int id;
    private String fullName;
    private List<String> courses;
}