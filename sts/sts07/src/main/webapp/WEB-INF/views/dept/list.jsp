<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<h1>부서목록</h1>
</div>
<table class="table">
	<thead>
		<tr>
			<th>deptno</th>
			<th>dname</th>
			<th>loc</th>
		</tr>
	<thead>
	<tbody>
		<c:forEach items="${list }" var="bean">
		<tr>
				<td><a href="./${bean.deptno }">${bean.deptno }</a></td>
				<td><a href="./${bean.deptno }">${bean.dname }</a></td>
				<td><a href="./${bean.deptno }">${bean.loc }</a></td>
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