<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>  
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class = "container py-4">
		<jsp:include page="/project/menu.do" />
	
		<%!
			String greeting = "Goods Market";
			String tagline = "Welcome to Goods Market";
		%>
		<div class="p-5 mb-4 bg-body-tertiary rounded-3">
			<div class="container-fluid py-5">
				<h1 class="display-5 fw-bold"><%=greeting %></h1>
				<p class="col-md-8 fs-4">Web Project</p>
			</div>
		</div>
		
		<div class="row align-items-md-stretch text-center">
			<div class="col-md-12">
				<div class="h-100 p-5">
					<h3><%=tagline %></h3>
					
				</div>
			</div>
		</div>
		
		<jsp:include page="/project/footer.do" />
	</div>
</body>
</html>





