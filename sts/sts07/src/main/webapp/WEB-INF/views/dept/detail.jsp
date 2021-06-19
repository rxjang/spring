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
	<h1>입력 페이지</h1>
</div>
<form class="form-horizontal" method="post" action="./">
  <div class="form-group">
    <label for="deptno" class="col-sm-2 control-label">deptno</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="deptno" value="${bean.deptno }" name="deptno" readonly>
    </div>
  </div>
  <div class="form-group">
    <label for="dname" class="col-sm-2 control-label">dname</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="dname" value="${bean.dname }" name="dname" readonly>
    </div>
  </div>
  <div class="form-group">
    <label for="loc" class="col-sm-2 control-label">loc</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="loc" value="${bean.loc }" name="loc" readonly>
    </div>
  </div>
</form>
<div>
	<a href="./${bean.deptno }/edit" class="btn btn-default" role="btn">수정</a>
<form method="post">
<input type="hidden" name="_method" value="delete"/>
	<button class="btn-danger">삭제</button>
</form>
</div>
<%@ include file="../template/footer.jspf" %>
</body>
</html>