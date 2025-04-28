<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서수정</title>
<link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container py-4">
		<jsp:include page="/project/menu.do"/>
			<div class="p-5 mb-4 bg-body-tertiary rounded-3">
				<div class="container-fluid py-5">
					<h1 class="display-5 fw-bold">상품수정</h1>
					<p class="col-md-8 fs-4">Product Updating</p>
				</div>
			</div>
			<%@ include file="dbconn.jsp" %>
			<%
				String productId = request.getParameter("id");
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = "SELECT * FROM product WHERE p_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, productId);
				rs = pstmt.executeQuery();
				if(rs.next()){
			%>
			<div class="row align-items-md-stretch">
				<div class="col-md-5">
					<img alt="image" src="/images/<%=rs.getString("p_fileName")%>" style="width: 100%">
				</div>
				
				<div class="col-md-7">
					<form action="/project/processUpdateProduct.do" name="newProduct" method="post" enctype="multipart/form-data" class="form-horizontal">
					<div class="mb-3 row">
						<label class="col-sm-2">상품코드</label>
						<div class="col-sm-5">
							<input id="productId" type="text" name="productId" class="form-control" value='<%=rs.getString("p_id")%>'>
						</div>
					</div>
					
					<div class="mb-3 row">
						<label class="col-sm-2">상품명</label>
						<div class="col-sm-5">
							<input id="name" type="text" name="name" class="form-control" value='<%=rs.getString("p_name")%>'>
						</div>
					</div>
					
					<div class="mb-3 row">
						<label class="col-sm-2">가격</label>
						<div class="col-sm-5">
							<input id="price" type="text" name="price" class="form-control" value='<%=rs.getString("p_price")%>'>
						</div>
					</div>
					
					<div class="mb-3 row">
						<label class="col-sm-2">출시일</label>
						<div class="col-sm-5">
							<input type="text" name="releaseDate" class="form-control" value='<%=rs.getString("p_releaseDate")%>'>
						</div>
					</div>
					
					<div class="mb-3 row">
		               <label class="col-sm-2">상세정보</label>
		               <div class="col-sm-5">
		                  <textarea id="description" name="description" rows="2" cols="50"
		                  class="form-control" placeholder="5자 이상 적어주세요" value='<%=rs.getString("p_description")%>'></textarea>
		               </div>
		            </div>
					
					<div class="mb-3 row">
						<label class="col-sm-2">분류</label>
						<div class="col-sm-5">
							<input type="text" name="category" class="form-control" value='<%=rs.getString("p_category")%>'>
						</div>
					</div>
					
					<div class="mb-3 row">
						<label class="col-sm-2">재고</label>
						<div class="col-sm-5">
							<input id="unitsInStock" type="text" name="unitsInStock" class="form-control" value='<%=rs.getString("p_soldout")%>'>
						</div>
					</div>
					
					<div class="mb-3 row">
						<label class="col-sm-2">이미지</label>
						<div class="col-sm-8">
							<input type="file" name="productImage" class="form-control">
						</div>
					</div>
					
					<div class="mb-3 row">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" class="btn btn-primary" value="등록">
						</div>
					</div>
				</form>
				</div>	
			</div>
			<%
				}
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			%>
			<jsp:include page="/project/footer.do"/> 
		</div>
</body>
</html>