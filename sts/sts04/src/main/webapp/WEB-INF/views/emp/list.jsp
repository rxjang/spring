<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../template/header.jspf" %>

</head>
<body>
<%@ include file="../template/menu.jspf" %>
<div class="page-header">
  <h1>LIST PAGE<small>EMP TABLE</small></h1>
</div>
<div class="list-group">
	<c:forEach items="${list }" var="bean">
	  <a href="${bean.sabun }" class="list-group-item">
		  <h4 class="list-group-item-heading">${bean.name }</h4>
		  <p class="list-group-item-text">${bean.nalja }</p>
	  </a>
	</c:forEach>
</div>
 <a href="add" class="btn btn-primary btn-block" role="btn">입 력</a>
<%@ include file="../template/footer.jspf" %>
</body>
</html>