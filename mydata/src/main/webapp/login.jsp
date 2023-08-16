<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	Cookie []c=request.getCookies();
	if(c!=null)
	{
		for(Cookie C :c)
		{
			if(C.getName().equals("loginerror"))
				out.print("<p>"+C.getValue()+"</p>");
		}
	}
%>
<form action="http://localhost:8080/Shoppingapp/logincheck" method="post">
Enter the UserName <input type="text" name="uid"/><br/>
Enter the Password <input type="password" name="pass"/><br/>
<input type="submit" Value="Submit"/>
<input type="reset" Value="Reset"/><br/>
<a href="http://localhost:8080/Shoppingapp/registration.jsp">New User</a>
</form>
</body>
</html>