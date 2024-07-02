package com.betrybe.fitness.controller;

import com.betrybe.fitness.dto.WorkoutCreationDto;
import com.betrybe.fitness.dto.WorkoutDto;
import com.betrybe.fitness.service.FitnessService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fitness")
public class FitnessController implements FitnessControllerInterface {

  private final FitnessService service;

  @Autowired
  public FitnessController(FitnessService service) {
    this.service = service;
  }

  @GetMapping
  public String getString() {
    return "Boas vindas à API de Fitness!";
  }

  @GetMapping("/workouts/{id}")
  public ResponseEntity<Optional<WorkoutDto>> getWorkoutById(@PathVariable Long id) {
    Optional<WorkoutDto> workout = service.getWorkout(id);
    if (workout.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(workout);
  }

  @PostMapping("/workouts")
  public ResponseEntity<WorkoutDto> createWorkout(@RequestBody WorkoutCreationDto workout) {
    WorkoutDto response = service.saveWorkout(workout);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
