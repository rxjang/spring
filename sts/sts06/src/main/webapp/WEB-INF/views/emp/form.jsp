<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../template/head.jspf" %>
<script type="text/javascript">
$(function(){
	if('${title}'=='Add'){
		$('.form-group').eq(2).remove();
		$('.form-group').eq(0).remove();
	}
});
</script>
</head>
<body>
<div class="page-header">
	<h1>${title } page<small>EMP</small></h1>
</div>
<%@ include file="../template/header.jspf" %>
<form class="form-horizontal" method="post">
  <div class="form-group">
    <label for="sabun" class="col-sm-2 control-label">sabun</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="sabun" id="sabun" placeholder="${bean.sabun }">
    </div>
  </div>
  <div class="form-group">
    <label for="name" class="col-sm-2 control-label">name</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="name" id="name" placeholder="${bean.name }">
    </div>
  </div>
  <div class="form-group">
    <label for="nalja" class="col-sm-2 control-label">nalja</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="nalja" id="nalja" placeholder="${bean.nalja }">
    </div>
  </div>
  <div class="form-group">
    <label for="pay" class="col-sm-2 control-label">pay</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="pay" id="pay" placeholder="${bean.pay }">
    </div>
  </div>
  <div class="form-group">
    <label for="deptno" class="col-sm-2 control-label">deptno</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="deptno" id="deptno" placeholder="${bean.deptno }">
    </div>
  </div>
  <div class="form-group">
      <button type="submit" class="btn btn-default">${title eq 'Add'?'입력':'수정' }</button>
  </div>
</form>
<%@ include file="../template/footer.jspf" %>
</body>
</html>