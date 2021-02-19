<%! public String genrateWishMessage(String user){
	//get System Date and time
	java.util.Calendar cal=java.util.Calendar.getInstance();
	
	//get curren hour of the day
	int hour=cal.get(java.util.Calendar.HOUR_OF_DAY);
	//generate wish message
// 	int a=34+56;
	if(hour<12)
		 return "Good Morning :: "+user;
	else if(hour<16)
		 return "Good Afternoon :: "+user;
	else if(hour<20)
		 return "Good Evening :: "+user;
	else
		return "Good Night :: "+user;
}
%>
<!-- <h1 style="color:red;text-align:center">Welcome to jsp pages</h1> -->
<h1 style="color:red;text-align:center">Date and time ::<%=new java.util.Date()%><br>
<% String user=request.getParameter("uname");%><br>
request object type ::<%=request.getClass() %><br>
out object type ::<%=out.getClass() %>
</h1>
<%-- <h3 style="color:green;text-align:center">The Wish Message is ::<%=genrateWishMessage(user) %> --%>
</h3>