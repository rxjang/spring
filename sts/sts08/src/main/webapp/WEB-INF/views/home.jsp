<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css"/>
	<script type="text/javascript" src="resources/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.js"></script>
	<script type="text/javascript">
	
	$(function(){
		$('nav a').eq(0).click(function(){
			console.log("logo");
			return false;
		});
		$('nav a').eq(1).click(function(){
			console.log("home");
			return false;
		});
		$('nav a').eq(2).click(function(){
			console.log('login');
			return false;
		});
		$('.dropdown-menu a').click(function(){
			console.log($(this).text());
			if($(this).text()=='dept'){
				listPage();
			}
			return false;
		});
		
		
	});
	
	function listPage(){
		$('.jumbotron').before('<div><h2 class="page-header">DEPT LIST</h2></div>').hide();
		$('.jumbotron').after('<div id="content"></div>');
		$('#content').html('<table class="table"><thead><tr><th>deptno</th><th>dname</th><th>loc</th></tr></thead><tbody></tbody></table>');
		$.getJSON('dept/',function(data){
			var array=data.root;
			console.log(array);
			for(var i=0; i<array.length; i++)
			$('#content table tbody').append('<tr><td>'+array[i].deptno
					+'</td><td>'+array[i].dname+'</td><td>'+array[i].loc+'</td></tr>');
		});
		
	}
	</script>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">비트교육센터</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">HOME <span class="sr-only">(current)</span></a></li>
        <li><a href="#">LOGIN</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">TABLE <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">emp</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">dept</a></li>
          </ul>
        </li>
      </ul>
      <form class="navbar-form navbar-right">
        <div class="form-group">
          <label for="sabun">sabun</label>
          <input type="text" class="form-control" id="sabun" placeholder="sabun">
        </div>
        <div class="form-group">
          <label for="deptno">deptno</label>
          <input type="text" class="form-control" id="deptno" placeholder="deptno">
        </div>
        <button type="submit" class="btn btn-default">LOGIN</button>
      </form>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div class="jumbotron">
	<h1>Hello world!  </h1>
	<P>  The time on the server is ${serverTime}. </P>
</div>


</body>
</html>