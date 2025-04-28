<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인메뉴</title>
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700&display=swap" rel="stylesheet">
<style>
body {
    background: #fff;
    color: #23272f;
    font-family: 'Montserrat', 'Noto Sans KR', sans-serif;
    margin: 0;
}
header {
    background: #fff;
    border-bottom: 2px solid #e0e0e0;
    box-shadow: 0 2px 10px rgba(0,0,0,0.03);
}
.navbar {
    display: flex;
    align-items: center;
    justify-content: space-between;
    max-width: 1200px;
    margin: 0 auto;
    padding: 18px 32px;
}
.navbar-left {
    display: flex;
    align-items: center;
    gap: 10px;
}
.navbar-logo {
    display: flex;
    align-items: center;
    color: #00bfff;
    font-size: 1.7rem;
    font-weight: 700;
    text-decoration: none;
    letter-spacing: 1px;
}
.navbar-menu {
    display: flex;
    gap: 32px;
}
.navbar-menu a {
    color: #23272f;
    text-decoration: none;
    font-weight: 500;
    font-size: 1.1rem;
    transition: color 0.2s, border-bottom 0.2s;
    position: relative;
    padding-bottom: 2px;
}
.navbar-menu a:hover {
    color: #00bfff;
    border-bottom: 2px solid #00bfff;
}
.user-info {
    display: flex;
    align-items: center;
    gap: 16px;
    font-size: 1rem;
    color: #555;
    font-weight: 600;
}
@media (max-width: 800px) {
    .navbar-menu {
        gap: 16px;
        font-size: 1rem;
    }
    .navbar {
        padding: 12px 8px;
    }
}
</style>
</head>
<body>
<%
    String sessionId = (String) session.getAttribute("sessionId");
    Integer sessionLevel = (Integer) session.getAttribute("sessionLevel");
%>
<c:set var="sessionLevel" value="<%= sessionLevel %>" scope="page"/>
<header>
    <div class="navbar">
    	<div class="navbar-left">
	        <a href="<c:url value='/project/welcome.do' />" class="navbar-logo">
	            <!-- Home SVG 아이콘 -->
	            <svg width="32" height="32" fill="currentColor" viewBox="0 0 16 16" style="margin-right:10px;">
	                <path d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L8 2.207l6.646 6.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.707 1.5Z"/>
	                <path d="m8 3.293 6 6V13.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5V9.293l6-6Z"/>
	            </svg>
	            Home
	        </a>
	        <c:if test="${not empty sessionId}">
		        <span class="user-info">[${sessionId}님]</span>
		    </c:if>
	    </div>
        <nav class="navbar-menu">
            <a href="<c:url value='/project/products.do' />">상품목록</a>
            
            <c:choose>
                <c:when test="${empty sessionId}">
                    <a href="<c:url value='/project/member/loginMember.do' />">로그인</a>
                    <a href="<c:url value='/project/member/addMember.do' />">회원가입</a>
                </c:when>
                <c:otherwise>
                    <a href="<c:url value='/project/cart.do' />">장바구니</a>
           			<a href="<c:url value='/project/delivery.do' />">배송조회</a>
                    <a href="<c:url value='/project/member/logoutMember.do' />">로그아웃</a>
                    <a href="<c:url value='/project/member/updateMember.do' />">정보수정</a>
                    <c:if test="${sessionLevel == 9}">
                        <a href="<c:url value='/project/editProduct.do' />">상품편집</a>
                        <a href="<c:url value='/project/addProduct.do' />">상품등록</a>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </nav>
    </div>
</header>
</body>
</html>
