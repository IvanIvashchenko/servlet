package it.sevenbits;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;

public class FirstServlet extends HttpServlet {

    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        //set the MIME type of the response, "text/html"
        response.setContentType("text/html");

        //use a PrintWriter send text data to the client who has requested the servlet
        PrintWriter out = response.getWriter();

        //Begin assembling the HTML content
        out.println("<html><head>");

        out.println("<title>Help Page</title></head><body>");
        out.println("<h2>Please submit your information</h2>");

        //make sure method="post" so that the servlet service method
        //calls doPost in the response to this form submit
        out.println(
            "<form method=\"post\" action =\"" + request.getContextPath() +
                "/firstservlet\" >");

        out.println("<table border=\"0\"><tr><td valign=\"top\">");
        out.println("Your first name: </td>  <td valign=\"top\">");
        out.println("<input type=\"text\" name=\"firstName\" size=\"20\">");
        out.println("</td></tr><tr><td valign=\"top\">");
        out.println("Your last name: </td>  <td valign=\"top\">");
        out.println("<input type=\"text\" name=\"lastName\" size=\"20\">");
        out.println("</td></tr><tr><td valign=\"top\">");
        out.println("Your email: </td>  <td valign=\"top\">");
        out.println("<input type=\"text\" name=\"email\" size=\"20\">");
        out.println("</td></tr><tr><td valign=\"top\">");

        out.println("<input type=\"submit\" value=\"Submit Info\"></td></tr>");
        out.println("</table></form>");
        out.println("</body></html>");
    }

    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        //display the parameter names and values
        Enumeration paramNames = request.getParameterNames();

        String parName;//this will hold the name of the parameter from the HTML form

        boolean emptyEnum = false;
        if (!paramNames.hasMoreElements()) {
            emptyEnum = true;
        }

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        Repository repository = new Repository();
        try {
            repository.insert(firstName, lastName, email);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //set the MIME type of the response, "text/html"
        response.setContentType("text/html");

        //use a PrintWriter send text data to the client who has requested the servlet
        PrintWriter out = response.getWriter();

        //Begin assembling the HTML content
        out.println("<html><head>");
        out.println("<title>Submitted Parameters</title></head><body>");

        if (emptyEnum){
            out.println("<h2>Sorry, the request does not contain any parameters</h2>");
        } else {
            out.println("<h2>Here are the submitted parameter values</h2>");
        }

        while(paramNames.hasMoreElements()){

            parName = (String) paramNames.nextElement();
            out.println("<strong>" + parName + "</strong> : " + request.getParameter(parName));
            out.println("<br />");
        }

        out.println("</body></html>");

    }
}
