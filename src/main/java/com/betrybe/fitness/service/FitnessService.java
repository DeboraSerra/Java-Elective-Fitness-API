package com.betrybe.fitness.service;

import com.betrybe.fitness.database.FakeFitnessDatabase;
import com.betrybe.fitness.dto.WorkoutCreationDto;
import com.betrybe.fitness.dto.WorkoutDto;
import com.betrybe.fitness.model.Workout;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Fitness service.
 */
@Service
public class FitnessService implements FitnessServiceInterface {

  private final FakeFitnessDatabase database;

  /**
   * Instantiates a new Fitness service.
   *
   * @param database the database
   */
  @Autowired
  public FitnessService(FakeFitnessDatabase database) {
    this.database = database;
  }

  private WorkoutDto transformWorkoutToWotkoutDto(Workout workout) {
    WorkoutDto result = new WorkoutDto(workout.getId(), workout.getName(),
        workout.getRepetitions());
    return result;
  }

  @Override
  public WorkoutDto saveWorkout(WorkoutCreationDto newWorkoutDto) {
    Workout workout = new Workout();
    workout.setName(newWorkoutDto.name());
    workout.setRepetitions(newWorkoutDto.repetitions());
    workout.setSecretTechnique(newWorkoutDto.secretTechnique());
    Workout response = database.saveWorkout(workout);
    WorkoutDto workoutDto = transformWorkoutToWotkoutDto(response);
    return workoutDto;
  }

  @Override
  public Optional<WorkoutDto> getWorkout(Long id) {
    Optional<Workout> result = database.getWorkout(id);
    if (result.isEmpty()) {
      return Optional.empty();
    }
    WorkoutDto workout = transformWorkoutToWotkoutDto(result.get());
    return Optional.of(workout);
  }

  @Override
  public List<WorkoutDto> getAllWorkouts() {
    List<Workout> workouts = database.getAllWorkouts();
    ArrayList<WorkoutDto> workoutDtos = new ArrayList<WorkoutDto>();
    for (Workout workout : workouts) {
      workoutDtos.add(new WorkoutDto(workout.getId(), workout.getName(), workout.getRepetitions()));
    }
    return workoutDtos;
  }
}
