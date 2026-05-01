package com.serverlet.project;

import com.serverlet.project.DB.AddStudentServerlet;
import com.serverlet.project.DB.GetAllStudentServerlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws LifecycleException {
        System.out.println( "Hello World!" );
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config.xml");
        Tomcat tomcat = new Tomcat();
        tomcat.getConnector();
        Context context = tomcat.addContext("",new File(".").getAbsolutePath());
        Tomcat.addServlet(context,"HtmlServerlet",new HtmlServerlet());
        Tomcat.addServlet(context,"JsonServerlet",new JsonServerlet());
        GetAllStudentServerlet getAllStudentServerlet = new GetAllStudentServerlet();
        JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
        getAllStudentServerlet.setJdbcTemplate(jdbcTemplate);
        AddStudentServerlet addStudentServerlet = new AddStudentServerlet();
        addStudentServerlet.setJdbcTemplate(jdbcTemplate);
        Tomcat.addServlet(context,"AddStudentServerlet",addStudentServerlet);
        Tomcat.addServlet(context,"GetAllStudentServerlet",getAllStudentServerlet);
        context.addServletMappingDecoded("/html","HtmlServerlet");
        context.addServletMappingDecoded("/json","JsonServerlet");
        context.addServletMappingDecoded("/getAllStudentServerlet","GetAllStudentServerlet");
        context.addServletMappingDecoded("/addStudentServerlet","AddStudentServerlet");
        tomcat.start();
        tomcat.getServer().await();

    }
}
