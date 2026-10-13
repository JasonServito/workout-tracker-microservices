-- Write database for the AddWorkout service
CREATE TABLE IF NOT EXISTS Workout (
    workoutID INT AUTO_INCREMENT PRIMARY KEY,
    workoutDate DATE NOT NULL,
    duration INT NOT NULL,
    userID INT NOT NULL
);
