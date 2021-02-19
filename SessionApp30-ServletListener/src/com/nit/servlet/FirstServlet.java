package com.nit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/firsturl")
public class FirstServlet extends HttpServlet {
	
   
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//get PrintWriter obj
		PrintWriter pw=res.getWriter();
		//set ContentType
		res.setContentType("text/html");
		//read form data
		String  name=req.getParameter("pname");
		String father=req.getParameter("fname");
		String ms=req.getParameter("ms");
		
		//Create HttpSession object and Set Cookies Attribute
		HttpSession ses=req.getSession(true);
		ses.setAttribute("name",name);
		ses.setAttribute("father", father);
		ses.setAttribute("ms", ms);
		ses.setMaxInactiveInterval(2*60);
		//Generate form2 dynamically based on martial status value
		pw.println("<body bgcolor='yellow'>");
		if(ms.equalsIgnoreCase("married")) {
			pw.println("<h1 style='color:red;text-align:center'> provide married life Details(form2)  </h1>");
			pw.println("<form action='"+res.encodeURL("secondurl")+"' method='POST'>");
			pw.println("<table bgcolor='cyan' align='center'>");
			pw.println("<tr><td>Spouse name ::</td><td><input type='text' name='f2t1'></td></tr>");
			pw.println("<tr><td>No.of kids :: </td><td><input type='text' name='f2t2'></td></tr>");
			pw.println("<tr><td colspan='2'><input type='submit' value='submit'> </td></tr>");
			pw.println("</table>");
			pw.println("</form>");
		}
		else {
			pw.println("<h1 style='color:red;text-align:center'> Provide Single life Details(form2)  </h1>");
			pw.println("<form action='" +res.encodeURL("secondurl")+ "'method='POST'>");
			pw.println("<table bgcolor='cyan' align='center'>");
			pw.println("<tr><td>when do u want to marry?</td><td><input type='text' name='f2t1'></td></tr>");
			pw.println("<tr><td>Why do u want to marry? </td><td><input type='text' name='f2t2'></td></tr>");
			pw.println("<tr><td colspan='2'><input type='submit' value='submit'> </td></tr>");
			pw.println("</table>");
			pw.println("</form>");
		}
		
		pw.println("<h1> Session object id :: "+ses.getId()+"</h1>");
		boolean bo=ses.isNew();
		pw.println("<h1>session New or Note :: "+bo+"</h1>");
		pw.println("</body>");
		//close stream
		pw.close();
		
	}//doGet

	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
