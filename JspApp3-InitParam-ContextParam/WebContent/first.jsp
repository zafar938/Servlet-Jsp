


<%! public void jspInit(){

	//String dbuser=config.getInitParameter("dbuser");
	//String dbpwd=application.getInitParameter("dbuser");
	
	ServletConfig cg=getServletConfig();
	ServletContext sc=getServletContext();
	
	System.out.println("dbuser name::"+cg.getInitParameter("dbuser"));
    System.out.println("dbpwd name::"+sc.getInitParameter("dbpwd"));
    try{
    Class.forName("java.util.Date");
    System.out.println("driver loaded");
    }
    catch(Exception e){
    	e.printStackTrace();
    }
}
%>
<h1 style="color:red;text-align:Cetner"> WElcome to jsp  pages </h1>
  <h1>           
             init param (dbuser ) value:: <%=config.getInitParameter("dbuser") %> <br>
             context param (dbpwd ) value:: <%=application.getInitParameter("dbpwd") %> <br>
             
             <%  Class.forName("java.util.Date");
                     System.out.println("driver loaded");
             %>

</h1>