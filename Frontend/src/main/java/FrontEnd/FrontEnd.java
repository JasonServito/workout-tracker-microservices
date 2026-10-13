package FrontEnd;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "FrontEnd", urlPatterns = {"/FrontEnd"})
public class FrontEnd extends HttpServlet {
    private Authenticate auth;

    public FrontEnd() {
        auth = new Authenticate();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String token = auth.authenticate(username, password);

        System.out.println("JWT Token: " + token);

        if (token != null) {
            HttpSession session = request.getSession();
            session.setAttribute("authToken", token);
            session.setAttribute("username", username);
            response.sendRedirect("frontPageWithLogin.jsp");
        } else {
            request.setAttribute("error", "Invalid credentials");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
