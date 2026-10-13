package Business;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import Helper.Workout;

@WebServlet("/addWorkoutServlet")
public class AddWorkoutServlet extends HttpServlet {
    private AddWorkout workoutService = new AddWorkout(); // Object that adds workouts

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Get user input from the form
        String date = request.getParameter("date");
        String duration = request.getParameter("duration");
        String userID = request.getParameter("userID");

        // Set response type as XML
        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        
        boolean success = workoutService.addWorkout(date, duration, userID); // Call the add workout method
        if (success) {
            Workout workout = new Workout(); // Class to get Workout attributes as XML
            workout.setDate(date);
            workout.setDuration(duration);
            workout.setUserID(userID);
            
            // Write success message with workout details
            response.getWriter().write("<response><message>Workout added successfully</message><workout><date>" + workout.getDate() + "</date><duration>" + workout.getDuration() + "</duration><userID>" + workout.getUserID() + "</userID></workout></response>");
        } else {
            response.getWriter().write("<response><message>Error adding workout</message></response>");
        }
    }
}
