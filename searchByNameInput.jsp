<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>book/searchInput</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/bootstrap.min.css">
</head>
<body>
	<h1>BOOK_NAMEで検索</h1>
	<form action="searchByNameResult">
		<input class="form-control" type="text" name="bookName" placeholder="BOOK_NAMEを入力してください。">
		<input class="bnt btn-warning" type="submit" value="検索">
	</form>
</body>
</html>