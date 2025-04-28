<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ page import="java.util.*, org.big.dto.*, org.big.dao.*" %>  

<%@ include file="dbconn.jsp" %>
<%
	String id = request.getParameter("id");
	if(id == null || id.trim().equals("")){
		response.sendRedirect("/project/products.do");
		return;
	}
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Product product = null;
    
    try{
    	String sql = "SELECT * FROM product WHERE p_id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        rs = pstmt.executeQuery();
    	
    	if(rs.next()){
    		product = new Product();
    		product.setProductId(rs.getString("p_id"));
    		product.setName(rs.getString("p_name"));
    		product.setPrice(rs.getInt("p_price"));
    		product.setReleaseDate(rs.getString("p_releaseDate"));
    		product.setDescription(rs.getString("p_description"));
    		product.setCategory(rs.getString("p_category"));
    		product.setSoldout(rs.getInt("p_soldout"));
            product.setFilename(rs.getString("p_filename"));
        }
    }catch(SQLException e){
    	e.getMessage();
    }finally{
    	if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		if(conn != null) conn.close();
    }

    if (product == null) {
        response.sendRedirect("exceptionNoProductId.jsp");
        return;
    }
	
	ArrayList<Product> list = (ArrayList<Product>) session.getAttribute("cartlist");
	if(list == null){
		list = new ArrayList<Product>();
		session.setAttribute("cartlist", list);
	}
	
	int cnt = 0;
	Product goodsQnt = new Product();
	
	for(int i = 0; i<list.size(); i++) {
		goodsQnt = list.get(i);
		if(goodsQnt.getProductId().equals(id)){
			cnt++;
			int orderQuantity = goodsQnt.getQuantity() + 1;
			goodsQnt.setQuantity(orderQuantity);
			break;
		}
	}

	if(cnt == 0){
		product.setQuantity(1);
		list.add(product);
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