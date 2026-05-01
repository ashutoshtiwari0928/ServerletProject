package com.serverlet.project;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws LifecycleException {
        System.out.println( "Hello World!" );
        Tomcat tomcat = new Tomcat();
        tomcat.getConnector();
        Context context = tomcat.addContext("",new File(".").getAbsolutePath());
        Tomcat.addServlet(context,"HtmlServerlet",new HtmlServerlet());
        Tomcat.addServlet(context,"JsonServerlet",new JsonServerlet());
        context.addServletMappingDecoded("/html","HtmlServerlet");
        context.addServletMappingDecoded("/json","JsonServerlet");
        tomcat.start();
        tomcat.getServer().await();

    }
}
