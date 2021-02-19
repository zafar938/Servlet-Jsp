package com.nit.listener;



import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class RequestProcessingAnalyzerListener implements ServletRequestListener {

     private long start,end;
    public RequestProcessingAnalyzerListener() {
    	System.out.println("RequestProcessingAnalyzerListener:: 0-param constructor");
        
    }
    
    @Override
    public void requestInitialized(ServletRequestEvent sre)  { 
    	System.out.println("RequestProcessingAnalyzerListener.requestInitialized()");
    	start=System.currentTimeMillis();
        
    }

	@Override
    public void requestDestroyed(ServletRequestEvent sre)  { 
		System.out.println("RequestProcessingAnalyzerListener.requestDestroyed()");
         end=System.currentTimeMillis();
         //get HttpServletRequest obj
         HttpServletRequest req=(HttpServletRequest)sre.getServletRequest();
         System.out.println(req.getRequestURL()+"has taken "+(end-start)+" ms time process the request");
         //write to Server's log file
         ServletContext sc=req.getServletContext();
         sc.log(req.getRequestURL()+" has taken "+(end-start)+" ms time process the request");
    }
	
}
