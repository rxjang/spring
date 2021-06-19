<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<h2>page1 page</h2>
	<nav>
		<a href="/login/">page1</a>
		<a href="/login/emp/page2">page2</a>
		<a href="/login/dept/page3">page3</a>
		<a href="/login/page4">page4</a>
	</nav>
	<div>
		<form action="result" method="post">
			<div>
				<label for="id">id</label>
				<input type="text" name="id" id="id">
			</div>
			<div>
				<label for="pw">pw</label>
				<input type="text" name="pw" id="pw">
			</div>
			<div>
				<button>로그인</button>
			</div>
		</form>
	</div>
</body>
</html>
