<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<sql:setDataSource 
    var="dataSource"
    url="jdbc:mysql://localhost:3306/project"
    driver="com.mysql.cj.jdbc.Driver" 
    user="root" 
    password="1111" 
/>

<sql:query dataSource="${dataSource}" var="resultSet">
    SELECT * FROM MEMBER WHERE id=? AND password=?
    <sql:param value="${param.id}" />
    <sql:param value="${param.password}" />
</sql:query>

<c:forEach var="row" items="${resultSet.rows}">
    <!-- JSTL로 세션 설정 -->
    <c:set var="sessionId" value="${row.id}" scope="session" />
    <c:set var="sessionLevel" value="${row.level}" scope="session" />
    <c:redirect url="/project/member/resultMember.do?msg=2" />
</c:forEach>

<c:redirect url="/project/member/loginMember.do?error=1" />
