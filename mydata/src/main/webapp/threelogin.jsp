<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="http://localhost:8080/Shoppingapp/three" method="get">
<%
	Cookie [] C=request.getCookies();
      if(C!=null)
      {
    	  for(Cookie c: C)
    	  {
    		  if(c.getName().equals("loginerror"))
    		  {
    			  out.print("<p>"+c.getValue()+"</p>");
    		  }
    	  }
      }
%>
Enter the UserName <input type="text" name="uid"/><br/>
Enter the Password <input type="password" name="pass"/><br/>
<input type="submit" Value="Submit"/>
<input type="reset" Value="Reset"/><br/>

</body>
</html>