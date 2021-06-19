<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  <h1>${title eq null?'ADD':title } PAGE <small>EMP TABLE</small></h1>
</div>
<form class="form-horizontal" method="post">
	<input type="hidden" name="sabun" value="${bean.sabun eq null?0:bean.sabun }"/>
  <div class="form-group">
    <label for="name" class="col-sm-2 control-label">name</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="name" id="name" placeholder="name" value="${bean.name }">
    </div>
  </div>
  <div class="form-group">
    <label for="pay" class="col-sm-2 control-label">pay</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="pay" id="pay" placeholder="pay" value="${bean.pay }">
    </div>
  </div>
  <div class="form-group">
    <label for="deptno" class="col-sm-2 control-label">deptno</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="deptno" id="deptno" placeholder="deptno" value="${bean.deptno }">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">입 력</button>
    </div>
  </div>
</form>

<%@ include file="../template/footer.jspf" %>
</body>
</html>