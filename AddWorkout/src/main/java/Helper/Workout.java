package Helper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "workout") 
@XmlAccessorType(XmlAccessType.FIELD)
public class Workout {

    private String workoutID;
    private String date;
    private String duration;
    private String userID;

    public Workout(String workoutID, String date, String duration, String userID) {
        this.workoutID = workoutID;
        this.date = date;
        this.duration = duration;
        this.userID = userID;
    }

    public Workout() {
        this.workoutID = "";
        this.date = "";
        this.duration = "";
        this.userID = "";
    }

    public String getWorkoutID() { return workoutID; }
    public void setWorkoutID(String workoutID) { this.workoutID = workoutID; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }

    }

