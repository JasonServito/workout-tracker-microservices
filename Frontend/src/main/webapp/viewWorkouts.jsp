<%@ page contentType="text/html;charset=UTF-8" session="false" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("authToken") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String responseXml = (String) session.getAttribute("workoutSearchResults");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Workouts Found</title>
</head>
<body>
    <h2>Workout Results</h2>
    <div>
        <%= responseXml %>
    </div>
</body>
</html>
