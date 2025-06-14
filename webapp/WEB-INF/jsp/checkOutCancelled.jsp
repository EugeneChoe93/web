<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문취소</title>
<link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container py-4">
	<jsp:include page="/project/menu.do" />
		<div class="p-5 mb-4 bg-body-tertiary rounded-3">
			<div class="container-fluid py-5">
				<h1 class="display-5 fw-bold">주문취소</h1>
				<p class="col-md-8 fs-4">Order Cancellation</p>
			</div>
		</div>
		
		<div class="row align-items-md-stretch">
			<h2 class="alert alert-danger">주문이 취소되었습니다.</h2>
		</div>
		
		<div class="container">
			<p> <a href="/project/products.do" class="btn btn-secondary">&laquo;상품목록</a>
		</div>
		<jsp:include page="/project/footer.do" />
	</div>
</body>
</html>