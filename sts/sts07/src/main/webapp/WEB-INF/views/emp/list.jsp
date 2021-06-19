<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../template/head.jspf" %>
</head>
<body>
<%@ include file="../template/header.jspf" %>
<div class="page-header">
	<h1>사원목록</h1>
</div>
<table class="table">
	<thead>
		<tr>
			<th>sabun</th>
			<th>name</th>
			<th>nalja</th>
			<th>pay</th>
			<th>dname</th>
		</tr>
	<thead>
	<tbody>
		<c:forEach items="${list }" var="bean">
		<tr>
				<td><a href="#">${bean.sabun }</a></td>
				<td><a href="#">${bean.name }</a></td>
				<td><a href="#">${bean.nalja }</a></td>
				<td><a href="#">${bean.pay }</a></td>
				<td><a href="#">${bean.dname }</a></td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<p>
	<a href="./add" class="btn btn-primary btn-block" role="btn">입 력</a>
</p>
<%@ include file="../template/footer.jspf" %>
</body>
</html>