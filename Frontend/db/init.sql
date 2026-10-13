-- User database for the Frontend service
CREATE TABLE IF NOT EXISTS User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Demo account for local testing (username: testuser, password: testpass)
INSERT INTO User (username, password) VALUES ('testuser', 'testpass');
