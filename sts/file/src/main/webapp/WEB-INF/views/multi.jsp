<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#file').click(function(){
			$(this).append('<input type="file" name="files"/><br/>')
		});
	});
</script>
</head>
<body>
	<h1>멀티파일업로드</h1>
	<form action="uploads" method="post" enctype="multipart/form-data">
		<div>
			<label for="sabun">sabun</label>
			<input type="text" name="sabun" id="sabun"/>
		</div>
		<div id="file">
			<input type="file" name="files"/><br/>
			<input type="file" name="files"/><br/>
		</div>
		<div>
			<button type="submit">전송</button>
		</div>
	</form>
</body>
</html>