package Helper;

import java.util.ArrayList;
import java.util.List;

public class Workout {
    private String workoutID;
    private String workoutDate;
    private String userID;
    private List<Exercise> exercises;
    private String duration;

    public Workout() {
        this.exercises = new ArrayList<>();
    }

    // Constructor to initialize workout attributes
    public Workout(String workoutID, String workoutDate, String duration, String userID) {
        this.workoutID = workoutID;
        this.workoutDate = workoutDate;
        this.duration = duration;
        this.userID = userID;
        this.exercises = new ArrayList<>();
    }

    // Getter and Setter methods
    public String getWorkoutID() {
        return workoutID;
    }

    public void setWorkoutID(String workoutID) {
        this.workoutID = workoutID;
    }

    public String getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(String workoutDate) {
        this.workoutDate = workoutDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    // Add exercise to the workout
    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    // Merge the exercises from another result row of the same workout
    public void addWorkout(Workout workout) {
        this.exercises.addAll(workout.getExercises());
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getExerciseDuration() {
        int totalDuration = 0;
        for (Exercise exercise : exercises) {
            totalDuration += exercise.getDuration(); // Assuming Exercise class has getDuration method
        }
        return totalDuration;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "workoutID='" + workoutID + '\'' +
                ", workoutDate='" + workoutDate + '\'' +
                ", userID='" + userID + '\'' +
                ", exercises=" + exercises +
                '}';
    }
}
