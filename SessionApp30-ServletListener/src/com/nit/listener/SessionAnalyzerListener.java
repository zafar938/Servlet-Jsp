package com.nit.listener;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


@WebListener
public class SessionAnalyzerListener implements HttpSessionListener {

   private long start, end;
    public SessionAnalyzerListener() {
        System.out.println("SessionAnalyzerListener :: 0-param cotructor");
    }

	
    public void sessionCreated(HttpSessionEvent se)  { 
         System.out.println("SessionAnalyzerListener.sessionCreated()");
        start=System.currentTimeMillis();
        System.out.println("Session started at ::"+new Date());
        //write to log file
        ServletContext sc=se.getSession().getServletContext();
        sc.log("Session started at :: "+new Date());
    }

	
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	System.out.println("SessionAnalyzerListener.sessionDestroyed()");
    	end=System.currentTimeMillis();
           System.out.println("Session end at :: "+new Date());
           //write  to log file
           ServletContext sc=se.getSession().getServletContext();
           sc.log("Session Ended at :: "+new Date()+ "Session duration ::"+(end-start)+" ms");
         
    }
	
}
