<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Workouts</title>
</head>
<body>
    <h2>Search Workouts</h2>
    <form action="SearchWorkoutServlet" method="get">
        Enter Date: <input type="text" name="query" required><br><br>
        <input type="submit" value="Search">
    </form>
</body>
</html>
