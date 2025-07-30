package com.example.tplus_jv250321_md05_session01_bai01;

import java.io.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
//    private LocalDateTime message;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        String formattedTime = now.format(formatter);

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Thoi gian hien tai: " + formattedTime + "</h1>");
        out.println("</body></html>");
    }
}