package Endpoint;

import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import Business.SearchBusiness;
import Helper.WorkoutsXML;

@Path("search")  // Base path for the search functionality
public class SearchResource {

    @Context
    private UriInfo context;

    public SearchResource() {}

    // Endpoint to search for workouts
    @GET
    @Path("/{query}")  // Path for searching workouts by query (e.g., /search/2023-06-15)
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")  // Producing XML response
    public String getXml(@PathParam("query") String query) {
        // Search for workouts based on the query
        SearchBusiness search = new SearchBusiness();
        WorkoutsXML workouts = search.getWorkoutsByQuery(query);

        try {
            // Convert the workouts object into XML format
            JAXBContext jaxbContext = JAXBContext.newInstance(WorkoutsXML.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  // Pretty print XML

            // Create a string writer to hold the XML
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(workouts, sw);

            return sw.toString();  // Return the XML string as response

        } catch (JAXBException ex) {
            // Log the error if XML conversion fails
            Logger.getLogger(SearchResource.class.getName()).log(Level.SEVERE, "❌ Error during XML conversion", ex);
            return "<error>XML processing failed</error>";
        }
    }
}
