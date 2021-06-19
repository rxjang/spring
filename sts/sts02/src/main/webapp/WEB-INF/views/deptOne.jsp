<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" 
	href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css"/>
<script type="text/javascript" 
	src="${pageContext.request.contextPath }/resources/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" 
	src="${pageContext.request.contextPath }/resources/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		if('${readonly}'){
			$('form').submit(function(){
			alert('이동');
				location.href='edit?deptno=${bean.deptno}';
				return false;
			});
			$('form button').eq(1).click(function(){
				$.post('delete','deptno=${bean.deptno}',function(data){
					if(data){
						alert("ERROR");
					}else{
						location.replace("list");
					}
				});
			});
		}else{
			$('form button').eq(1).text('취 소')
									.attr('type','reset')
									.addClass('btn-default')
									.removeClass('btn-danger');
		}
		
	});
</script>
</head>
<body>
<div class="navbar navbar-default" id="menu">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">
        비트교육센터
      </a>
    </div>
    <ul class="nav navbar-nav">
        <li><a href="${pageContext.request.contextPath }/">HOME</a></li>
        <li><a href="${pageContext.request.contextPath }/emp/list">EMP</a></li>
        <li class="active"><a href="${pageContext.request.contextPath }/dept/list">DEPT</a></li>
        <li><a href="${pageContext.request.contextPath }/login/">LOGIN</a></li>
    </ul>
  </div>
</div>
<div class="jumbotron">
  <h1>${title1 }페이지!</h1>
</div>
<div class="container">
	<div class="row" id="header">
	  <div class="col-md-12">
	  	<div class="page-header">
		  <h1>DEPT ${title2 } <small>DEPT Table</small></h1>
		</div>
	  </div>
	</div>
	<div class="row" id="content">
	  <div class="col-md-12">
	  
		<form class="form-horizontal" method="post">
		  <div class="form-group">
		    <label for="deptno" class="col-sm-2 control-label">deptno</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" name="deptno" id="deptno" value="${bean.deptno }" readonly/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="dname" class="col-sm-2 control-label">dname</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" name="dname" id="dname" value="${bean.dname }" ${readonly }/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="loc" class="col-sm-2 control-label">loc</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" name="loc" id="loc" value="${bean.loc }" ${readonly }/>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-default">수 정</button>
		      <button type="button" class="btn btn-danger">삭 제</button>
		    </div>
		  </div>
		</form>
		
	  </div>
	</div>
	<div class="row" id="footer">
	  <div class="col-md-12">
	  	<address>비트캠프 서울시 서초구 강남대로 459 (서초동, 백암빌딩)</address>
		Copyright &copy; 비트캠프 All rights reserved.
	  </div>
	</div>
</div>
</body>
</html>

