<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서아이디 오류</title>
<link href="./resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container py-4">
		<%@ include file="menu.jsp" %>
		
		<div class="p-5 mb-4 bg-body-tertiary rounded-3">
			<div class="container-fluid py-5">
				<h1 class="alert alert-danger">해당 상품이 존재하지 않습니다.</h1>
			</div>
		</div>
		
		<div class="row align-items-md-stretch text-center">
			<div class="row justify-content-center align-items-center">
				<div class="h-100 p-5">
					<p><%=request.getRequestURL() %>?<%=request.getQueryString() %>
					<p><a href="products.jsp" class="btn btn-secondary">목록 &raquo;</a>
				</div>
			</div>
		</div>
		<%@ include file="footer.jsp" %>
	</div>
</body>
</html>