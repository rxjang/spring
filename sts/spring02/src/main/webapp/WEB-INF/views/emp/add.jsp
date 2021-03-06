<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
	<script type="text/javascript" src="../js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="../js/bootstrap.min.js"></script>
	<script type="text/javacript">
	</script>
</head>
<body>
<div class="container">
  	<div class="row" id="header">
  		<div class="col-md-12">
  			<h1>비트 교육 센터</h1>
  		</div>
	</div>
  	<div class="row" id="menu">
  		<div class="col-md-12 text-uppercase bg-info text-center">
  			<a href="/spring02/index.bit">home</a>
  			<a href="/spring02/emp/list.bit">emp</a>
  			<a href="/spring02/dept/list.bit">dept</a>
  			<a href="/spring02/login.bit">login</a>
  		</div>
	</div>
  	<div class="row" id="content">
  		<div class="col-md-12">
  			<form class="form-horizontal" method="post">
		  <div class="form-group">
		    <label for="sub">sub</label>
		    <input type="text" class="form-control" id="sub" name="sub" placeholder="제목">
		  </div>
		  <div class="form-group">
		    <label for="name">name</label>
		    <input type="text" class="form-control" id="name" name="name" placeholder="이름">
		  </div>
		  <div class="form-group">
		    <label for="pay">pay</label>
		    <input type="text" class="form-control" id="pay" name="pay" placeholder="금액">
		  </div>
		  <button type="submit" class="btn btn-default">입력</button>
		</form>
  			
  		</div>
	</div>
  	<div class="row" id="footer">
  		<div class="col-md-12 text-center">
  		<address><strong>비트캠프</strong> 서울시 서초구 강남대로 459 (서초동, 백암빌딩)</address>
		(주)비트컴퓨터 서초본원 대표이사 : 조현정 / 문의 : 02-3486-9600 / 팩스 : 02-6007-1245<br/>
		통신판매업 신고번호 : 제 서초-00098호 / 개인정보보호관리책임자 : 최종진<br/>
		Copyright &copy; 비트캠프 All rights reserved.
  		</div>
	</div>
</div>
</body>
</html>
