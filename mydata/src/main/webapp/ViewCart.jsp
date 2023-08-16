<%@page import="java.util.*,java.sql.*"%>
<%@page import="java.sql.Connection"%>
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
	Connection con;
	con = (Connection) config.getServletContext().getAttribute("jdbccon");
	PreparedStatement ps;
	ResultSet rs;
	HttpSession session1=request.getSession();
    List<Integer> sps=(List<Integer>)session.getAttribute("cart");
	if(sps==null)
	{%>
		 <p>No product are Selected</p>
		<%
		
	}
	   
		float price = 0;
			int no=0;
			 %>
			 
			    <h1>Products are in cart's</h1>
			<%
			ps=con.prepareStatement("select * from product where p_id=?"); %>
			<table border='1' style='border-collapse:collapse'>
			<%
			for(int nh :sps)
			{
				ps.setInt(1, nh);
				rs=ps.executeQuery();
				if(rs.next())
				{
					 %>
					 
					<tr>
					<td><%=(++no)%></td>
					<td><%=rs.getString(2) %></td>
					<td><%= rs.getFloat(4) %></td>
					</tr>
					
					<% price+=rs.getFloat(4); %>
					<%
				}
			} %>
			<tr>
				<td colspan='2'> Total price </td>
				<td><%= price %></td><%
				session.setAttribute("Tprice",price);%>
		<tr>
		</table>
			<br/> <br/> <a href='confirmcart'> Confirm Cart <a/>
			<br/> <br/> <a href='homeservlet'> Go back to Categories <a/>
	
</body>
</html>