package Helper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "workouts")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkoutsXML {

    @XmlElement(name = "workout")
    private List<Workout> workouts;

    public WorkoutsXML() {
        this.workouts = new ArrayList<>();
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }
}
