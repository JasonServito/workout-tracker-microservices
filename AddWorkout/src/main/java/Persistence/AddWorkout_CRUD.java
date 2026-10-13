package Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;

import Helper.Workout;

public class AddWorkout_CRUD {

    // Method to add a new workout to the database
    public static boolean addWorkout(String workoutDate, String duration, String userID) {
        boolean inserted = false;
        String sql = "INSERT INTO Workout (workoutDate, duration, userID) VALUES (?, ?, ?)";

        try (Connection con = DBConfig.getCon();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, workoutDate);
            stmt.setInt(2, Integer.parseInt(duration));  // Assuming duration is in minutes
            stmt.setInt(3, Integer.parseInt(userID));

            int rowsAffected = stmt.executeUpdate();
            inserted = rowsAffected > 0;
            System.out.println("Workout Inserted: " + workoutDate);

        } catch (SQLIntegrityConstraintViolationException e) {
            // Handle duplicate workout error
            System.out.println("Workout already exists: " + workoutDate);
            return false; // Return false instead of crashing

        } catch (Exception e) {
            System.out.println("Error inserting workout: " + e.getMessage());
        }
        return inserted;
    }

    // Method to retrieve a workout by its ID
    public static Workout getWorkout(int workoutID) {
        Workout workout = new Workout();
        String sql = "SELECT * FROM Workout WHERE workoutID = ?";

        try (Connection con = DBConfig.getCon(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, workoutID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                workout.setWorkoutID(rs.getString("workoutID"));
                workout.setDate(rs.getString("workoutDate"));
                workout.setDuration(rs.getString("duration"));
                workout.setUserID(rs.getString("userID"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving workout", e);
        }
        return workout;
    }

    // Method to retrieve all workouts
    public static ArrayList<Workout> getAllWorkouts() {
        ArrayList<Workout> workouts = new ArrayList<>();
        String sql = "SELECT * FROM Workout";
        try (Connection con = DBConfig.getCon(); 
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Workout workout = new Workout();
                workout.setWorkoutID(rs.getString("workoutID"));
                workout.setDate(rs.getString("workoutDate"));
                workout.setDuration(rs.getString("duration"));
                workout.setUserID(rs.getString("userID"));
                workouts.add(workout);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving workouts", e);
        }
        return workouts;
    }
}
