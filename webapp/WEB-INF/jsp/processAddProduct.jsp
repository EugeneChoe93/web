<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.oreilly.servlet.*, com.oreilly.servlet.multipart.*, java.util.*, java.sql.*" %> 
<%@ page import="jakarta.servlet.*" %>
<%@ include file="dbconn.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");	
	
	String filename = "";
	String realFolder = application.getRealPath("/resources/images");
	int maxSize = 5*1024*1024;
	String encType = "utf-8";
	
	MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
	String productId = multi.getParameter("productId");
	String name = multi.getParameter("name");
	String price = multi.getParameter("price");
	String description = multi.getParameter("description");
	String category = multi.getParameter("category");
	String releaseDate = multi.getParameter("releaseDate");
	String soldout = multi.getParameter("soldout");
	
	Enumeration files = multi.getFileNames();
	String fname = (String)files.nextElement();
	String fileName = multi.getFilesystemName(fname);
	
	int p_price;
	
	if(price.isEmpty()){
		p_price = 0;
	}else{
		p_price = Integer.valueOf(price);
	}
	
	long stock;
	
	if(soldout.isEmpty()){
		stock = 0;
	}else{
		stock = Long.valueOf(soldout);
	}
	
	PreparedStatement pstmt = null;
	
	String sql = "INSERT INTO product VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, productId);
	pstmt.setString(2, name);
	pstmt.setInt(3, p_price);
	pstmt.setString(4, category);
	pstmt.setString(5, description);
	pstmt.setString(6, releaseDate);
	pstmt.setLong(7, stock);
	pstmt.setString(8, fileName);
	
	int num = pstmt.executeUpdate();
	if(num == 1){
		out.println("정상 입력되었습니다.");
	}else{
		out.println("입력이 실패하였습니다.");
	}
	
	if(pstmt != null) pstmt.close();
	if(conn != null) conn.close();
	
	response.sendRedirect("/project/products.do");
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