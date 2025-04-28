<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.big.dto.*, org.big.dao.*, java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript">
	function addToCart() {
		if (confirm("상품을 장바구니에 추가하시겠습니까?")) {
			document.addForm.submit();
		} else {
			document.addForm.reset();
		}
	}
</script>
</head>
<body>
	<div class="container py-4">
	<%@ include file="dbconn.jsp" %>
	 <jsp:include page="/project/menu.do" />
		<div class="p-5 mb-4 bg-body-tertiary rounded-3">
			<div class="container-fluid py-5">
				<h1 class="display-5 fw-bold">상품정보</h1>
				<p class="col-md-8 fs-4">ProductInfo</p>
			</div>
		</div>
		<%
			String id = request.getParameter("id");
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM product WHERE p_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
		%>
		<%
		    String sessionId = (String) session.getAttribute("sessionId");
		    boolean isLoggedIn = (sessionId != null && !sessionId.isEmpty());
		%>
		
		<div class="row align-items-md-stretch">
			<div class="col-md-5">
				<img alt="image.jsp" src="/images/<%=rs.getString("p_fileName") %>" style="width:70%">
			</div>
		
			<div class="col-md-6">
				<h3><b><%=rs.getString("p_name") %></b></h3>
				<p><%=rs.getString("p_description") %>
				<p><b>상품코드 : </b><span class="badge text-bg-danger"><%=rs.getString("p_id") %></span>
				<p><b>출시일 : </b><%=rs.getString("p_releaseDate") %>
				<p><b>분류 : </b><%=rs.getString("p_category") %>
				<p><b>재고 : </b><%=rs.getString("p_soldout") %>
				<h4><%=rs.getString("p_price") %>원</h4>
				<p><form name="addForm" action="/project/addCart.do?id=<%=rs.getString("p_id")%>" method="post">
				<%
				    if (isLoggedIn) {
				%>
				    <a href="#" class="btn btn-info" onclick="addToCart()">주문 &raquo;</a>
				    <a href="/project/cart.do" class="btn btn-warning">장바구니 &raquo;</a>
				<%
				    }
				%>
				<a href="/project/products.do" class="btn btn-secondary">목록 &raquo;</a>
				<a href="/QnaListAction.do" class="btn btn-secondary">Q&A &raquo;</a>
					</form>
			</div>
		<%
			}
			
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		%>
			
		</div>
		<jsp:include page="/project/footer.do" />
	</div>
</body>
</html>