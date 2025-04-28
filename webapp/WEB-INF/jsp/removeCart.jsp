<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, org.big.dao.*" %>
<%@ page import="org.big.dto.*" %>       

<%
	String id = request.getParameter("id");
	if(id == null || id.trim().equals("")){
		response.sendRedirect("/project/products.do");
		return;
	}
	
	ArrayList<Product>cartList = (ArrayList<Product>)session.getAttribute("cartlist");
	Product goodsQnt = new Product();
	
	for(int i = 0; i< cartList.size(); i++) {
		goodsQnt = cartList.get(i);
		if(goodsQnt.getProductId().equals(id)){
			cartList.remove(goodsQnt);
		}
	}
	
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