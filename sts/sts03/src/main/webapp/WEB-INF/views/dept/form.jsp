<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Home</title>
	<link rel="stylesheet" type="text/css" 
		href="${pageContext.request.contextPath }/resources/css/bootstrap.css"/>
	<script type="text/javascript" 
	src="${pageContext.request.contextPath }/resources/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" 
	src="${pageContext.request.contextPath }/resources/js/bootstrap.js"></script>
	<script type="text/javascript">
	$(function(){
		if( '${title}' =='Detail'){
			$(':text').prop('readonly',true);
		}else if( '${title}' == 'Add'){
			$('form>.form-group').first().remove();
			$(':submit').text('입 력');
			$('form').removeAttr('action');
		}
		
	});
	</script>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#droplist" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">비트교육센터</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="droplist">
      <ul class="nav navbar-nav">
        <li><a href="${pageContext.request.contextPath }/">HOME <span class="sr-only">(current)</span></a></li>
        <li><a href="${pageContext.request.contextPath }/login">LOGIN</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">TABLE <span class="caret"></span></a>
          <ul class="dropdown-menu active">
            <li><a href="${pageContext.request.contextPath }/dept/">DEPT LIST</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="${pageContext.request.contextPath }/emp/">EMP LIST</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div class="container">
	<div class="row" id="header">
		<div class="col-md-12">
			<div class="page-header">
			  <h1>FORM PAGE <small>${title }</small></h1>
			</div>
		</div>
	</div>
	<div class="row" id="content">
		<div class="col-md-12">
			<!-- content start -->
			<form class="form-horizontal" method="post">
			  <div class="form-group">
			    <label for="deptno" class="col-sm-2 control-label">deptno</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" name="deptno" id="deptno" value="${bean.deptno }" placeholder="deptno">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="dname" class="col-sm-2 control-label">dname</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control"  name="dname" id="dname" value="${bean.dname }" placeholder="dname">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="loc" class="col-sm-2 control-label">loc</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control"  name="loc" id="loc" value="${bean.loc }" placeholder="loc">
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <button type="submit" class="btn btn-default">수 정</button>
			    </div>
			  </div>
			</form>
			<!-- content end -->
		</div>
	</div>
	<div class="row" id="footer">
		<div class="col-md-12">
			<address>비트캠프 서울시 서초구 강남대로 459 (서초동, 백암빌딩)</address>
					Copyright (c) 비트캠프 All rights reserved.
		</div>
	</div>
</div>
</body>
</html>