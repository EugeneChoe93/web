<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%
    String shipping_cartId = "";
    String shipping_name = "";
    String shipping_shippingDate = "";
    String shipping_country = "";
    String shipping_zipCode = "";
    String shipping_addressName = "";

    Cookie[] cookies = request.getCookies();
    if(cookies != null){
        for(int i = 0; i < cookies.length; i++){
            Cookie thisCookie = cookies[i];
            String n = thisCookie.getName();
            if(n.equals("Shipping_cartId")){
                shipping_cartId = URLDecoder.decode(thisCookie.getValue(), "utf-8");
            }
            if(n.equals("Shipping_name")){
                shipping_name = URLDecoder.decode(thisCookie.getValue(), "utf-8");
            }
            if(n.equals("Shipping_Date")){
                shipping_shippingDate = URLDecoder.decode(thisCookie.getValue(), "utf-8");
            }
            if(n.equals("Shipping_country")){
                shipping_country = URLDecoder.decode(thisCookie.getValue(), "utf-8");
            }
            if(n.equals("Shipping_zipCode")){
                shipping_zipCode = URLDecoder.decode(thisCookie.getValue(), "utf-8");
            }
            if(n.equals("Shipping_addressName")){
                shipping_addressName = URLDecoder.decode(thisCookie.getValue(), "utf-8");
            }
        }
    }
    boolean hasOrder = (shipping_cartId != null && !shipping_cartId.trim().isEmpty());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송조회</title>
<link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container py-4">
    <jsp:include page="/project/menu.do" />
    <div class="p-5 mb-4 bg-body-tertiary rounded-3">
        <div class="container-fluid py-5">
            <h1 class="display-5 fw-bold">배송조회</h1>
            <p class="col-md-8 fs-4">Delivery Tracking</p>
        </div>
    </div>
    <div class="row align-items-md-stretch">
        <% if(hasOrder) { %>
            <div class="alert alert-info">
                <h4>주문번호: <%= shipping_cartId %></h4>
                <p><b>수령인:</b> <%= shipping_name %></p>
                <p><b>배송예정일:</b> <%= shipping_shippingDate %></p>
                <p><b>배송지:</b> <%= shipping_country %> <%= shipping_zipCode %> <%= shipping_addressName %></p>
            </div>
        <% } else { %>
            <div class="alert alert-warning">
                <h4>배송정보가 없습니다.</h4>
                <p>최근 주문 내역이 없거나, 주문이 완료되지 않았습니다.</p>
            </div>
        <% } %>
    </div>
    <div class="container">
        <a href="/project/products.do" class="btn btn-secondary">&laquo; 도서목록</a>
    </div>
    <jsp:include page="/project/footer.do" />
</div>
</body>
</html>
