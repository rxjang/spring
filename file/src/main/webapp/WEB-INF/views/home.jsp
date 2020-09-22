<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	파일업로드
</h1>
	<form action="upload" method="post" enctype="multipart/form-data">
		<div>
			<label for="sabun">sabun</label>
			<input type="text" name="sabun" id="sabun">
		</div>
		<div>
			<label for="file1">file</label>
			<input type="file" name="file1" id="file1">
		</div>
		<div>
			<button>전송</button>
		</div>
	</form>

</body>
</html>
