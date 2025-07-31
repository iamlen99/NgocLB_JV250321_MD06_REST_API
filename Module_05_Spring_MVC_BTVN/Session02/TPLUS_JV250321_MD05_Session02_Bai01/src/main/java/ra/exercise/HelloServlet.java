package ra.exercise;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Gioi thieu ve ban than</h1>");
        out.println("<p><strong>Ho va ten: </strong>Le Bao Ngoc</p>");
        out.println("<p><strong>Tuoi: </strong>25</p>");
        out.println("<p><strong>So thich: </strong>Doc sach, du lich va lap trinh</p>");
        out.println("<p><strong>Gioi thieu: </strong>Toi la mot lap trinh vien dam me cong nghe, luon tim kiem co hoi de hoc hoi va phat trien ban than</p>");
        out.println("</body></html>");
    }

}