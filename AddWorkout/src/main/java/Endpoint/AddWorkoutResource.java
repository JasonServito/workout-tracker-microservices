package Endpoint;

import Helper.Workout;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import Business.AddWorkout;

/**
 * REST Web Service
 */
@Path("workout")
public class AddWorkoutResource {

    @Context
    private UriInfo context;

    // Retrieves a specific workout by ID
    @GET
    @Produces(MediaType.APPLICATION_XML) // XML output  
    @Path("/{workoutID}") 
    public String getWorkoutXml(@PathParam("workoutID") String workoutID) {
        System.out.println(workoutID);
        AddWorkout workoutBusiness = new AddWorkout();
        Workout workout = workoutBusiness.getWorkout(workoutID);  // Get the workout based on the workoutID

        // If no workout found, return empty response
        if (workout == null) {
            return "";
        }

        // Marshal the workout object into XML and return
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Workout.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // Format the output for readability

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(workout, sw);  // Convert workout object into XML

            return sw.toString();  // Return the workout in XML format

        } catch (JAXBException ex) {
            Logger.getLogger(AddWorkoutResource.class.getName()).log(Level.SEVERE, null, ex);
            return "<error>XML processing failed</error>";  // If JAXBException occurs, return error message
        }
    }

    // Add a new workout
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED) // Expect form data
    @Produces(MediaType.APPLICATION_XML)  // Return response in XML format
    public String addWorkout(@FormParam("date") String date,
                              @FormParam("duration") String duration,
                              @FormParam("userID") String userID) {
        AddWorkout workoutBusiness = new AddWorkout();
        boolean success = workoutBusiness.addWorkout(date, duration, userID);  // Call business logic to add the workout

        // Return appropriate XML response based on success or failure
        if (success) {
            return "<response><message>Workout added successfully</message></response>";
        } else {
            return "<response><message>Failed to add workout</message></response>";
        }
    }
}
