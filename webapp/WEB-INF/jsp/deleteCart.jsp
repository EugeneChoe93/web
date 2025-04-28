<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.big.dao.*" %>
<%@ page import="org.big.dto.*" %>  

<%
	String id = request.getParameter("cartId");
	if(id == null || id.trim().equals("")){
		response.sendRedirect("/project/cart.do");
		return;
	}
	
	session.invalidate();
	response.sendRedirect("/project/cart.do");
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>