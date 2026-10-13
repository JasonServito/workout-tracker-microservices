-- Read database for the SearchWorkouts service (filled from WORKOUT_ADDED events)
CREATE TABLE IF NOT EXISTS Workout (
    workoutID INT AUTO_INCREMENT PRIMARY KEY,
    workoutDate DATE NOT NULL,
    duration INT NOT NULL,
    userID INT NOT NULL
);

CREATE TABLE IF NOT EXISTS Exercise (
    exerciseID INT AUTO_INCREMENT PRIMARY KEY,
    exerciseName VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS Workout_Exercise (
    workoutID INT NOT NULL,
    exerciseID INT NOT NULL,
    PRIMARY KEY (workoutID, exerciseID),
    FOREIGN KEY (workoutID) REFERENCES Workout(workoutID),
    FOREIGN KEY (exerciseID) REFERENCES Exercise(exerciseID)
);
