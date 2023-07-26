package ru.astondevs.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InstructorDetailsDTO {
    private int id;
    private String youtubeChannel;
    private String instructor;
}