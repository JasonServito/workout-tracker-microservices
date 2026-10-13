package Business;

import Helper.Workout;
import Persistence.AddWorkout_CRUD;
import java.io.IOException;

public class AddWorkout {

    // Method to add a new workout
    public boolean addWorkout(String date, String duration, String userID) {
        boolean added = AddWorkout_CRUD.addWorkout(date, duration, userID);

        // Send message to a messaging queue (KubeMQ) if added
        if (added) {
            String message = "WORKOUT_ADDED:" + date + ":" + duration + ":" + userID;
            try {
                Messaging.sendMessage(message);
            } catch (IOException e) {
                System.out.println("❌ Failed to send message: " + e.getMessage());
            }
        }
        return added;
    }

    // Retrieve a workout by ID (helper method for Get functionality)
    public Workout getWorkout(int workoutID) {
        return AddWorkout_CRUD.getWorkout(workoutID);
    }

    public Workout getWorkout(String workoutID) {
        return getWorkout(Integer.parseInt(workoutID));
    }
}
