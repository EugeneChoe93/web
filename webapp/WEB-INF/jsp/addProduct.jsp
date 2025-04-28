<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품등록</title>
<link href="/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/js/validation.js"></script>
</head>
<body>
	<fmt:setLocale value='<%=request.getParameter("language") %>'/>
	<fmt:bundle basename="org.big.bundle.message">
		<div class="container py-4">
		<jsp:include page="/project/menu.do"/>
			<div class="p-5 mb-4 bg-body-tertiary rounded-3">
				<div class="container-fluid py-5">
					<h1 class="display-5 fw-bold"><fmt:message key="title"/></h1>
					<p class="col-md-8 fs-4">Product Addition</p>
				</div>
			</div>
			
			<div class="row align-items-md-stretch">
				<div class="text-end">
					<a href="?language=ko">한국어</a> | <a href="?language=en">English</a>
					<a href="/project/logout.do" class="btn btn-sm btn-success pull right">logout</a>	
				</div>
				<form action="/project/processAddProduct.do" name="newProduct" method="post" enctype="multipart/form-data" class="form-horizontal">
					<div class="mb-3 row">
						<label class="col-sm-2"><fmt:message key="productId"/></label>
						<div class="col-sm-3">
							<input id="productId" type="text" name="productId" class="form-control">
						</div>
					</div>
					
					<div class="mb-3 row">
						<label class="col-sm-2"><fmt:message key="name"/></label>
						<div class="col-sm-3">
							<input id="name" type="text" name="name" class="form-control">
						</div>
					</div>
					
					<div class="mb-3 row">
						<label class="col-sm-2"><fmt:message key="price"/></label>
						<div class="col-sm-3">
							<input id="price" type="text" name="price" class="form-control">
						</div>
					</div>
					
					<div class="mb-3 row">
						<label class="col-sm-2"><fmt:message key="releaseDate"/></label>
						<div class="col-sm-3">
							<input type="text" name="releaseDate" class="form-control">
						</div>
					</div>
					
					<div class="mb-3 row">
		               <label class="col-sm-2"><fmt:message key="description"/></label>
		               <div class="col-sm-5">
		                  <textarea id="description" name="description" rows="2" cols="50"
		                  class="form-control" placeholder="5자 이상 적어주세요"></textarea>
		               </div>
		            </div>
					
					<div class="mb-3 row">
						<label class="col-sm-2"><fmt:message key="category"/></label>
						<div class="col-sm-3">
							<input type="text" name="category" class="form-control">
						</div>
					</div>
					
					<div class="mb-3 row">
						<label class="col-sm-2"><fmt:message key="soldout"/></label>
						<div class="col-sm-3">
							<input id="soldout" type="text" name="soldout" class="form-control">
						</div>
					</div>
					
					<div class="mb-3 row">
						<label class="col-sm-2"><fmt:message key="productImage"/></label>
						<div class="col-sm-5">
							<input type="file" name="productImage" class="form-control">
						</div>
					</div>
					
					<div class="mb-3 row">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="button" class="btn btn-primary" value="<fmt:message key="button"/>" onclick="checkAddProduct()">
						</div>
					</div>
				</form>	
			</div>
			<jsp:include page="/project/footer.do"/> 
		</div>
	</fmt:bundle>
</body>
</html>

