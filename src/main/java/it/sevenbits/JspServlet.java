package it.sevenbits;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet which uses jsp for rendering data
 */
public class JspServlet extends HttpServlet {

    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        Repository repository = new Repository();
        List<User> users = new ArrayList<>();
        try {
            users = repository.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("users", users);
        request.setAttribute("name", "dataFromServer");
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }
}
