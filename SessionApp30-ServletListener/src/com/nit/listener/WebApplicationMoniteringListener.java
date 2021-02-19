package com.nit.listener;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class WebApplicationMoniteringListener implements ServletContextListener {
   private long start,end;
   
    public WebApplicationMoniteringListener() {
      System.out.println("WebApplicationMoniteringListener :: 0-param contructor");  
    }

	
    public void contextDestroyed(ServletContextEvent sce)  { 
         System.out.println("WebApplicationMoniteringListener.contextDestroyed()");
         end=System.currentTimeMillis();
         System.out.println("WebApplication Stopped/undeploye at :: "+new Date()+ "WebApplication runnning durations is ::"+(end-start)+" ms");
       //write in log file
         ServletContext sc=sce.getServletContext();
         sc.log(sc.getContextPath()+ " is Stopped/undeploye at "+new Date()+"WebApplication runnning durations is ::"+(end-start)+" ms");
    }

	
    public void contextInitialized(ServletContextEvent sce)  { 
        System.out.println("WebApplicationMoniteringListener.contextInitialized()");
        start=System.currentTimeMillis();
        System.out.println("WebApplication Started/deployed/reload/restart at :: "+new Date());
        //write to log files
        ServletContext sc=sce.getServletContext();
        sc.log(sc.getContextPath()+ " is Started/deployed/reload/restart at :: "+new Date());
    }
	
}
