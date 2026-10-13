<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Workout</title>
</head>
<body>
    <h2>Add Workout</h2>
    <form action="AddWorkoutServlet" method="post">
        Date (yyyy-mm-dd): <input type="text" name="date"><br><br>
        Duration (in minutes): <input type="text" name="duration"><br><br>
        User ID: <input type="text" name="userID"><br><br>
        <input type="submit" value="Add Workout">
    </form>
</body>
</html>
