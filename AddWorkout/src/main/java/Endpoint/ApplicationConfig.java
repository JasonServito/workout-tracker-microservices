package Endpoint;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("webresources")  // This replaces web.xml
public class ApplicationConfig extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        // Register the AddWorkout and SearchWorkout resource classes
        resources.add(Endpoint.AddWorkoutResource.class);  // Endpoint to add a workout
        resources.add(Endpoint.TestResource.class);  // Endpoint to search workouts
    }
}
