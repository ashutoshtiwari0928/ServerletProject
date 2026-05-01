package com.serverlet.project.DB;

import com.serverlet.project.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GetAllStudentServerlet extends HttpServlet {
    @Autowired
    JdbcTemplate jdbc;
    public void setJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws javax.servlet.ServletException, IOException {
        String sql = "select * from student";
        jdbc.execute("create table if not exists student (rollno int primary key, name varchar(50), marks int)");
        List<Student> list = jdbc.query(sql,( rs,  rowNum) -> {
                Student s = new Student();
                s.setRollno(rs.getInt("rollno"));
                s.setName(rs.getString("name"));
                s.setMarks(rs.getInt("marks"));
                return s;
        });
        System.out.println( list );
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        out.println(list);
    }
}
