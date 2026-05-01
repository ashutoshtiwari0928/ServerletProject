package com.serverlet.project;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HtmlServerlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws javax.servlet.ServletException, IOException {
        System.out.println("In service() HtmlServerlet");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<h1>Hello World!</h1><h2>Hello World!</h2>");
    }

}
