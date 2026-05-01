package com.serverlet.project;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonServerlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("In service() JsonServerlet");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.println("{\"name\":\"Ashutosh\",\"age\":23}");
    }
}
