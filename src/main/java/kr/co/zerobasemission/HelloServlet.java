package kr.co.zerobasemission;

import lombok.extern.log4j.Log4j2;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@Log4j2
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");

        log.info("아직 살아있다.");
    }

    public void destroy() {
    }
}