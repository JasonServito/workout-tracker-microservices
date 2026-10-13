package Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import Helper.Workout;
import Helper.Exercise;
import java.sql.SQLException;
import java.sql.Statement;

public class Workout_CRUD {

    // Optimized to return a Set<Workout> instead of just workout names
    public static Set<Workout> searchForWorkouts(String searchQuery) {
        Set<Workout> workouts = new HashSet<>();
        String sql = "SELECT W.workoutID, W.workoutDate, W.duration, W.userID, E.exerciseID, E.exerciseName " +
                     "FROM Workout W " +
                     "LEFT JOIN Workout_Exercise WE ON W.workoutID = WE.workoutID " +
                     "LEFT JOIN Exercise E ON WE.exerciseID = E.exerciseID " +
                     "WHERE W.workoutDate LIKE ?";  // Assuming you search by date

        try (Connection con = DBConfig.getCon();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, "%" + searchQuery + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String workoutID = String.valueOf(rs.getInt("workoutID"));
                String workoutDate = rs.getString("workoutDate");
                String duration = rs.getString("duration");
                String userID = rs.getString("userID");

                Workout workout = new Workout(workoutID, workoutDate, duration, userID);

                // Add exercises to the workout
                if (rs.getString("exerciseName") != null) {
                    Exercise exercise = new Exercise(
                            rs.getString("exerciseID"),
                            rs.getString("exerciseName"),
                            rs.getString("sets"),
                            rs.getString("reps"),
                            rs.getString("weight"), 
                            rs.getInt("duration")
                    );
                    workout.addExercise(exercise);
                }

                workouts.add(workout);
            }

        } catch (Exception e) {
            throw new RuntimeException("❌ Error searching workouts: " + e.getMessage(), e);
        }
        return workouts;
    }

    public static void insertWorkout(Workout workout) throws SQLException {
        try (Connection con = DBConfig.getCon()) {
            String insertWorkoutSQL = "INSERT INTO Workout (workoutDate, duration, userID) VALUES (?, ?, ?)";
            try (PreparedStatement workoutStmt = con.prepareStatement(insertWorkoutSQL, Statement.RETURN_GENERATED_KEYS)) {
                workoutStmt.setString(1, workout.getWorkoutDate());
                workoutStmt.setInt(2, Integer.parseInt(workout.getDuration()));
                workoutStmt.setString(3, workout.getUserID());
                workoutStmt.executeUpdate();
                try (ResultSet keys = workoutStmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        workout.setWorkoutID(keys.getString(1));
                    }
                }
            }

            String insertExerciseSQL = "INSERT INTO Exercise (exerciseName) VALUES (?) ON DUPLICATE KEY UPDATE exerciseName=exerciseName";
            String insertWE = "INSERT INTO Workout_Exercise (workoutID, exerciseID) SELECT ?, exerciseID FROM Exercise WHERE exerciseName=?";
            for (Exercise exercise : workout.getExercises()) {
                try (PreparedStatement exerciseStmt = con.prepareStatement(insertExerciseSQL)) {
                    exerciseStmt.setString(1, exercise.getExerciseName());
                    exerciseStmt.executeUpdate();
                }

                try (PreparedStatement weStmt = con.prepareStatement(insertWE)) {
                    weStmt.setString(1, workout.getWorkoutID());
                    weStmt.setString(2, exercise.getExerciseName());
                    weStmt.executeUpdate();
                }
            }
        }
    }
}
