<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Home</title>
<%@ include file="template/header.jspf" %>
<style tyoe="text/css">
	.item>img{
	}
	.carousel-indicators>li{
		
	}
</style>
<script type="text/javascript">
	$(function(){
		$('.carousel-indicators>li').first().addClass('active');
		$('.carousel-inner > .item').first().addClass('active');
	});
</script>
</head>
<body>
<%@ include file="template/menu.jspf" %>


<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
 	<c:forEach items="${imgs }" varStatus="status">
    	<li data-target="#carousel-example-generic" data-slide-to="${satus.index }"></li>
	</c:forEach>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
  <c:forEach items="${imgs }" var="img">
    <div class="item">
      <img src="resources/imgs/${img }" alt="img">
      <div class="carousel-caption">
		${img }
      </div>
    </div>
	</c:forEach>
  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>

<%@ include file="template/footer.jspf" %>
</body>
</html>
