package 软件体系结构实验三.BS结构;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Server extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Personal Address Book</h1>");
        out.println("<form action='Server' method='post'>");
        out.println("Name: <input type='text' name='name'><br>");
        out.println("Address: <input type='text' name='address'><br>");
        out.println("Phone: <input type='text' name='phone'><br>");
        out.println("<input type='submit' value='Add Contact'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        // Process the form data
        processFormData(name, address, phone);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Contact Added Successfully!</h2>");
        out.println("</body></html>");
    }

    private void processFormData(String name, String address, String phone) {
        // Process the form data (e.g., add to a database)
        System.out.println("Adding contact: " + name + ", " + address + ", " + phone);
    }
}