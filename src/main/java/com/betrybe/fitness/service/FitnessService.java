package com.betrybe.fitness.service;

import com.betrybe.fitness.database.FakeFitnessDatabase;
import com.betrybe.fitness.dto.WorkoutCreationDto;
import com.betrybe.fitness.dto.WorkoutDto;
import com.betrybe.fitness.model.Workout;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FitnessService implements FitnessServiceInterface {

  private final FakeFitnessDatabase database;

  @Autowired
  public FitnessService(FakeFitnessDatabase database) {
    this.database = database;
  }

  private WorkoutDto transformWorkoutToWotkoutDTO(Workout workout) {
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
    WorkoutDto workoutDto = transformWorkoutToWotkoutDTO(response);
    return workoutDto;
  }

  @Override
  public Optional<WorkoutDto> getWorkout(Long id) {
    Optional<Workout> result = database.getWorkout(id);
    if (result.isEmpty()) {
      return Optional.empty();
    }
    WorkoutDto workout = transformWorkoutToWotkoutDTO(result.get());
    return Optional.of(workout);
  }

  @Override
  public List<WorkoutDto> getAllWorkouts() {
    return List.of();
  }
}
