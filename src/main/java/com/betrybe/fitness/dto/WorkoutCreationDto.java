package com.betrybe.fitness.dto;

public record WorkoutCreationDto(
    String name,
    Integer repetitions,
    String secretTechnique) {

}
