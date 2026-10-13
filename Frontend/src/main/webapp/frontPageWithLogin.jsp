<%@ page contentType="text/html;charset=UTF-8" session="false" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("authToken") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <h2>Welcome, <%= session.getAttribute("username") %>!</h2>
    <p>You are now logged in.</p>

    <form action="addWorkout.jsp" method="get">
        <button type="submit">Add Workout</button>
    </form>

    <form action="searchWorkout.jsp" method="get">
        <button type="submit">Search Workout</button>
    </form>
</body>
</html>
