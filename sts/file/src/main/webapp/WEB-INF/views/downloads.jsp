<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>다운로드</h1>
	<ul>
		<c:forEach items="${names }" var="name">
			<li><a href="download/${name}">${name}</a></li>
		</c:forEach>
	</ul>
</body>
</html>