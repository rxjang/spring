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
    <label for="dname" class="col-sm-2 control-label">dname</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="dname" placeholder="dname" name="dname">
    </div>
  </div>
  <div class="form-group">
    <label for="loc" class="col-sm-2 control-label">loc</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="loc" placeholder="loc" name="loc">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-10">
      <button type="submit" class="btn btn-default">submit</button>
    </div>
  </div>

</form>
<%@ include file="../template/footer.jspf" %>
</body>
</html>