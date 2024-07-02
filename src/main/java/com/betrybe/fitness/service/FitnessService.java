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
  private FakeFitnessDatabase database;

  @Autowired
  public FitnessService(FakeFitnessDatabase database) {
    this.database = database
  }

  @Override
  public WorkoutDto saveWorkout(WorkoutCreationDto newWorkoutDto) {
    return null;
  }

  @Override
  public Optional<WorkoutDto> getWorkout(Long id) {
    Optional<Workout> result = database.getWorkout(id);
    if(result.isEmpty()) {
      return Optional.empty();
    }
    WorkoutDto workout = new WorkoutDto(result.get().getId(), result.get().getName(), result.get().getRepetitions());
    return Optional.of(workout);
  }

  @Override
  public List<WorkoutDto> getAllWorkouts() {
    return List.of();
  }
}
