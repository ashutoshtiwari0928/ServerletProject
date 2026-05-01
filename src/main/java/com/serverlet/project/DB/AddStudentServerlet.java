package com.serverlet.project.DB;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverlet.project.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class AddStudentServerlet extends HttpServlet {
    JdbcTemplate jdbcTemplate;
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddStudentServerlet");

        BufferedReader reader = req.getReader();
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Student s = objectMapper.readValue(sb.toString(), Student.class);
        System.out.println(s);
        jdbcTemplate.execute("create table if not exists student (rollno int primary key, name varchar(50), marks int)");
        String sql = "insert into student values (?, ?, ?)";
        jdbcTemplate.update(sql,s.getRollno(),s.getName(),s.getMarks());
        System.out.println(sb.toString());
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
