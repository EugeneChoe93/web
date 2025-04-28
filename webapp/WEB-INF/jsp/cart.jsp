<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.sql.*, org.big.dto.Product, org.big.dao.ProductRepository" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<link href="/css/bootstrap.min.css" rel="stylesheet">
<%
	String cartId = session.getId();
%>

<%-- <%=cartId %> --%>
</head>
<body>
	<div class="container py-4">
	<%@ include file="dbconn.jsp" %>
	<jsp:include page="/project/menu.do" />
		<div class="p-5 mb-4 bg-body-tertiary rounded-3">
			<div class="container-fluid py-5">
				<h1 class="display-5 fw-bold">장바구니</h1>
				<p class="col-md-8 fs-4">Cart</p>
			</div>
		</div>
		
		<div class="row align-items-md-stretch">
			<div class="row">
				<table width = "100%">
					<tr>
						<td align="left">
							<a href="/project/deleteCart.do?cartId=<%=cartId%>" class="btn btn-danger">삭제하기</a>
						</td>
						<td align="right">
							<a href="/project/shippingInfo.do?cartId=<%=cartId %>" class="btn btn-success">주문하기</a>
						</td>
					</tr>
				</table>
			</div>
		
			<div style="padding-top: 50px">
				<table class="table table-hover">
					<tr>
						<th>상품</th>
						<th>가격</th>
						<th>재고</th>
						<th>소계</th>
						<th>비고</th>
					</tr>
				<%
					int sum = 0;
					ArrayList<Product> cartList = (ArrayList<Product>) session.getAttribute("cartlist");
					
					if (cartList == null){
						cartList = new ArrayList<Product>();
					}
					
					for (int i = 0; i < cartList.size(); i++) { 						
						Product product = cartList.get(i);
						String productId = product.getProductId();
						
						if(productId != null && !productId.equals("")){
							String sql = "SELECT * FROM product WHERE p_id = ?";
							PreparedStatement pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, productId);
							ResultSet rs = pstmt.executeQuery();
							  
							if(rs.next()){
								int total = rs.getInt("p_price") * product.getQuantity();
	                            sum += total;
				%>
				<tr>
					
					<td><%=product.getProductId()%> - <%=product.getName()%></td>
					<td><%=product.getPrice()%></td>
					<td><%=product.getQuantity()%></td>
					<td><%=total%></td>
					<td><a href="/project/removeCart.do?id=<%=product.getProductId()%>" class="badge text-bg-danger">삭제</a></td>
				</tr>
				<%
							}	
						}
					}
					
				%>
				<tr>
					<th></th>
					<th></th>
					<th>총액</th>
					<th><%=sum%></th>
					<th></th>
				</tr>
			</table>

				<a href="/project/products.do" class="btn btn-secondary">
					&laquo; 쇼핑 계속하기
				</a>
			</div>
		</div>
		<jsp:include page="/project/footer.do" />
	</div>
</body>
</html>











