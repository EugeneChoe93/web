<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %> 

<%
	String edit = request.getParameter("edit");
	if (edit == null) edit = "";
%>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품수정</title>
<link href="/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript">
	function deleteConfirm(id){
		if (confirm("해당 상품을 삭제합니다.!!") == true) {
			location.href = "/project/deleteProduct.do?id=" + id;
		} else {
			return;
		}
	}
</script>
</head>
<body>
	<div class="container py-4">
	 <jsp:include page="/project/menu.do" />
		<div class="p-5 mb-4 bg-body-tertiary rounded-3">
			<div class="container-fluid py-5">
				<h1 class="display-5 fw-bold">상품편집</h1>
				<p class="col-md-8 fs-4">ProductEditing</p>
			</div>
		</div>
		<%@ include file="dbconn.jsp" %>
		
		
		<div class="row align-items-md-stretch text-center">
			<%
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = "SELECT * FROM product";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()){
			%>
			<div class="col-md-4">
	         <div class="h-100 p-2 round-3">
	         	<img alt="image.jpg" src="/images/<%=rs.getString("p_fileName")%>" style="width:250; height:350">
	            <h5><b><%=rs.getString("p_name")%></b></h5>
	            <%=rs.getString("p_releaseDate") %>
	            <p><%=rs.getString("p_description")%>....
	            <p><%=rs.getString("p_price") %>원
	            <p>
	            	<%
	            		if(edit.equals("update")) {
	            	%>
	            		<a href="/project/updateProduct.do?id=<%=rs.getString("p_id") %>" class="btn btn-success" role="button">수정 &raquo;</a>
	            	<%
	            		} else if(edit.equals("delete")){
	            	%>
	            		<a href="#" onclick="deleteConfirm('<%=rs.getString("p_id")%>')" class="btn btn-danger" role="button">삭제 &raquo;</a>
	            	<%
	            		}
	            	%>
	         </div>
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