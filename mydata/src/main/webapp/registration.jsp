<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="http://localhost:8080/Shoppingapp/registration" method="post">
  <table>
  	<tr>
  		
    <td>Enter the User id <input type="text" name="uid"/><br/></td>
    </tr>
    	<tr>
	<td>Enter the Password <input type="password" name="pass"/><br/></td>
	</tr>
		<tr>
	<td>Enter the First Name <input type="text" name="fname"/><br/></td>
	</tr>
	<tr>
	<td>Enter the Middle Name <input type="text" name="mname"/><br/></td>
	</tr>
	<tr>
	<td>Enter the Last Name <input type="text" name="lname"/><br/></td>
	</tr>
	<tr>
	<td>Enter the Email id <input type="email" name="email"/><br/></td>
	</tr>
	<tr>
	<td>Enter the Contact no <input type="text" name="no"/><br/></td>
	</tr>
	<tr>
	<td><input type="submit" value="Submit"/></td>
	<td><input type="reset" value="Reset"/></td>
	</tr>
  	
  	
  	
  	
  </table>
	
</form>

</body>
</html>