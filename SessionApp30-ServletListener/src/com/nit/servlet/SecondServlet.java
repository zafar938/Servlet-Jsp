package com.nit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


@WebServlet("/secondurl")
public class SecondServlet extends HttpServlet {
	private static final String INSERT_QUERY="INSERT INTO PERSON_INFO(PNAME,FATHERNAME,MS,INFO1,INFO2)VALUES(?,?,?,?,?)";
	@Resource(name="DsJndi-mysql")
     private DataSource ds;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//get PrintWriter object
		PrintWriter pw=res.getWriter();
		//set ContentType
		res.setContentType("text/html");
		//read form1 data
	     HttpSession ses=req.getSession(false);
	     String name=(String)ses.getAttribute("name");
	     String father=(String)ses.getAttribute("father");
	     String ms=(String)ses.getAttribute("ms");
		//read form2/req2  data
		String f2val1=req.getParameter("f2t1");
		String f2val2=req.getParameter("f2t2");
		try(Connection con=ds.getConnection()) {
			try(PreparedStatement ps=con.prepareStatement(INSERT_QUERY)){
				//set values to query params
				ps.setString(1,name); ps.setString(2, father);
				ps.setString(3, ms);  ps.setString(4, f2val1); ps.setString(5,f2val2);
				// execute the Query
				int result=ps.executeUpdate();
				//process the result
				pw.println("<body bgcolor='pink'>");
				if(result==0)
					pw.println("<h1 style='color:red;text-align:center'>Registration not completed </h1>");
				else
					pw.println("<h1 style='color:red;text-align:center'>Registration  completed </h1>");
				
				//get session id
				pw.println("<h1>session object id :: "+ses.getId()+"</h1>");
				long milli=ses.getCreationTime();
				Date date=new Date(milli);
				pw.println("<h1>session Creation Time :: "+date+"</h1>");
				long milli1=ses.getLastAccessedTime();
				Date date1=new Date(milli1);
				pw.println("<h1>session Creation Time :: "+date+"</h1>");
				boolean bo=ses.isNew();
				pw.println("<h1>session New or Note :: "+bo+"</h1>");
				//Invalidate the session
				ses.invalidate();
			}//try
		}//try
		catch(SQLException se) {
			pw.println("<b> Problem in  record insertion </b> ");
			se.printStackTrace();
		}
		catch(Exception e) {
			pw.println("<b> unknown DBProblem </b> ");
			e.printStackTrace();
		}
		
		//Generate dynamic web page displaying form1/req1 data  and form2/req2 data
		pw.println("<br><b>form1 /req1 data is ::"+name+"...."+father+"...."+ms+"</b>");
		pw.println("<br><br><b>form2 /req2 data is ::"+f2val1+"      "+f2val2+"</b>");
		// add home hyperlink
		pw.println("<br><h1> <a href='form.html'>HOME</a></h1>");
		pw.println("</body>");
		  
		  
		//close stream
		pw.close();
		
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doGet(req, res);
	}

}
