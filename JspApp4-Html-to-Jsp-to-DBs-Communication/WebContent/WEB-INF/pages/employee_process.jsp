<%@ page import="java.sql.*"%>

<%! 
	private Connection con;
    private PreparedStatement ps1,ps2;
    
public void jspInit(){
	//get access to ServletCongig obj
	ServletConfig sc=getServletConfig();
    //read jsp init parameter value
	String driver=sc.getInitParameter("driver");
    String url=sc.getInitParameter("url");
    String dbpwd=sc.getInitParameter("dbpwd");
    String dbuser=sc.getInitParameter("dbuser");
   
    try{
    	//load driver class
    	Class.forName(driver);
    	//get connection
    	con=DriverManager.getConnection(url,dbuser,dbpwd);
    	//create PreparedStatment obj
    	ps1=con.prepareStatement("INSERT INTO JSP_EMPLOYEE VALUES(JSP_SEQ_EMP.NEXTVAL,?,?,?)");
    	ps2=con.prepareStatement("SELECT EMPNO,ENAME,EADDS,SALARY FROM JSP_EMPLOYEE");
    	
    	//read form data
    	
    }
    catch(SQLException se){
    	se.printStackTrace();
    }
    catch(ClassNotFoundException cnf){
    	cnf.printStackTrace();
    }
    catch(Exception es){
    	es.printStackTrace();
    }
	
}
%>
<%
    //read s1 req pram value to know wheather request has come from submit button or hyperlink
    String s1Val=request.getParameter("s1");
    if(s1Val.equalsIgnoreCase("register")){
    	//read request Parameter
    	String name=request.getParameter("ename");
    	String adds=request.getParameter("eadd");
    	float salary=Float.parseFloat(request.getParameter("salary"));
    	//set value to Insert Quary Param
    	ps1.setString(1,name);
    	ps1.setString(2,adds);
    	ps1.setFloat(3,salary);
    	//execute query
    	int result=ps1.executeUpdate();
    	//process the result
    	if(result==1){ %>
    	<h1 style="color:green;text-align:center">Employee registered</h1>
    	<% }//if
    	else{ %>
    	<h1 style="color:green;text-align:center">Employee registered Failed</h1>
    	<% }//else
       }//if
       else{
    	  ResultSet rs=ps2.executeQuery(); %>
    	   <h1 style="color:red;text-align:center">Employee Details </h1>
    	   <table  border="1" align="center" bgcolor="pink">
    	     <tr> <th>EmpNo</th> <th>Ename</th> <th>Eaddress</th> <th>Salary</th> </tr>
    	     <%while(rs.next()){ %>
    	        <tr>
    	         <td> <%=rs.getInt(1) %></td>
    	         <td> <%=rs.getString(2) %></td>
    	         <td> <%=rs.getString(3) %></td>
    	         <td> <%=rs.getFloat(4) %></td>
    	        </tr>
    	     <%} %>
    	   </table>
    	  
        <%   } %>
        
        <br><br>
           <h1><a href="employee_form.html">HOME</a></h1>
    	
<%!
   public void jspDestroy(){
	
	   try{
		   if(ps1!=null)
			   ps1.close();
	   }
	   catch(SQLException se){
		   se.printStackTrace();
	   }
	   try{
		   if(ps2!=null)
			   ps2.close();
	   }
	   catch(SQLException se){
		   se.printStackTrace();
	   }
	   try{
		   if(con!=null)
			   con.close();
	   }
	   catch(SQLException se){
		   se.printStackTrace();
	   }
}
%>
