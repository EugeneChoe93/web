<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.oreilly.servlet.*, com.oreilly.servlet.multipart.*, java.util.*, java.sql.*" %> 
<%@ include file="dbconn.jsp" %>  

<%
	request.setCharacterEncoding("UTF-8");	
	
	String filename = "";
	String realFolder = "C:\\eclipse\\eclipse-workspace\\JspEx01\\src\\main\\webapp\\resources\\images";
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
	ResultSet rs = null;
	
	String sql = "SELECT * FROM product WHERE p_id = ?";
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, productId);
	rs=pstmt.executeQuery();
	
	if(rs.next()){
		if(fileName != null){
			sql = "UPDATE product SET p_name = ?, p_price = ?, p_description = ?, p_category = ?, p_soldout = ?, p_releaseDate = ?, p_fileName = ? WHERE p_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, p_price);
			pstmt.setString(3, description);
			pstmt.setString(4, category);
			pstmt.setLong(5, stock);
			pstmt.setString(6, releaseDate);
			pstmt.setString(7, fileName);
			pstmt.setString(8, productId);
			
			pstmt.executeUpdate();
		}else{
			sql = "UPDATE product SET p_name = ?, p_price = ?, p_description = ?, p_category = ?, p_soldout = ?, p_releaseDate = ?,  WHERE p_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, p_price);
			pstmt.setString(3, description);
			pstmt.setString(4, category);
			pstmt.setLong(5, stock);
			pstmt.setString(6, releaseDate);
			pstmt.setString(7, productId);
			
			pstmt.executeUpdate();
		}
	}
	
	if(pstmt != null) pstmt.close();
	if(conn != null) conn.close();
	
	response.sendRedirect("/project/editProduct.do?edit=update");
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