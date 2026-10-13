package Helper;

public class Exercise {
    private String exerciseID;
    private String exerciseName;
    private String sets;
    private String reps;
    private String weight;
    private int duration;

    public Exercise() {
    }

    // Constructor to initialize all fields
    public Exercise(String exerciseID, String exerciseName, String sets, String reps, String weight, int duration) {
        this.exerciseID = exerciseID;
        this.exerciseName = exerciseName;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.duration = duration; // Ensure duration is properly set
    }

    // Getters and Setters
    public String getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(String exerciseID) {
        this.exerciseID = exerciseID;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "exerciseID='" + exerciseID + '\'' +
                ", exerciseName='" + exerciseName + '\'' +
                ", sets='" + sets + '\'' +
                ", reps='" + reps + '\'' +
                ", weight='" + weight + '\'' +
                ", duration=" + duration +
                '}';
    }
}
