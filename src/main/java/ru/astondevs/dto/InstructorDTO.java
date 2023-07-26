package ru.astondevs.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class InstructorDTO {
    private int id;
    private String fullName;
    private String youtubeChannel;
    private List<String> courses;
}