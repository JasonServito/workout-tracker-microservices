package Business;

import Persistence.Workout_CRUD;
import java.util.*;
import Helper.Workout;
import Helper.WorkoutsXML;

public class SearchBusiness {

    // Method to get workouts by a query
    public WorkoutsXML getWorkoutsByQuery(String query) {
        // Call the CRUD method to search workouts based on the query (could be date, duration, userID)
        Set<Workout> workouts = Workout_CRUD.searchForWorkouts(query);
        Map<String, Workout> allWorkouts = new HashMap<>();

        System.out.println("✅ Number of workouts found: " + workouts.size());

        // Merge result rows that belong to the same workout
        for (Workout workout : workouts) {
            if (allWorkouts.containsKey(workout.getWorkoutID())) {
                allWorkouts.get(workout.getWorkoutID()).addWorkout(workout);
            } else {
                allWorkouts.put(workout.getWorkoutID(), workout);
            }
        }

        System.out.println("✅ Total Unique Workouts: " + allWorkouts.size());

        WorkoutsXML bs = new WorkoutsXML();
        bs.setWorkouts(new ArrayList<>(allWorkouts.values()));  // Converting the map values to a list

        return bs;  // Returning the workouts wrapped in WorkoutsXML
    }
}
