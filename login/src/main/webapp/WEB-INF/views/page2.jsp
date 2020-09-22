<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<h2>EMP page</h2>
	<nav>
		<a href="/login/">page1</a>
		<a href="/login/emp/page2">page2</a>
		<a href="/login/dept/page3">page3</a>
		<a href="/login/page4">page4</a>
		<c:if test="${sessionScope.login }">
			${sessionScope.who }님 로그인 중<a href="./logout">[logout]</a>
		</c:if>
		<c:if test="${sessionScope.login eq null }">
			<a href="./login">login</a>
		</c:if>
	</nav>
</body>
</html>
