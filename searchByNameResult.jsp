<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/bootstrap.min.css">
</head>
<body>
	<p>書籍名:<c:out value="${param.bookName}"/>
	の検索結果</p>
	<table class="table table-bordered table-dark">
		<tr>
			<th>書籍名</th>
			<th>価格</th>
			<th>発売日</th>
			<th>出版社名</th>
			<th>カテゴリー名</th>
		</tr>
		<c:forEach items="${bookList2}" var="b">
			<tr>
				<td ><c:out value="${b.bookName}"/></td>
				<td ><c:out value="${b.price}"/></td>
				<td ><fmt:formatDate pattern="yyyy-MM-dd" value="${b.onSaleDate}" /></td>
				<td ><c:out value="${b.publisherName}"/></td>
				<td > <c:out value="${b.categoryName}"/></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>