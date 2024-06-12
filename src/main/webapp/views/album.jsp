<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<c:choose>
	<c:when test="${albumListOrderedByDate != null}">
		<h2>新着</h2>
	</c:when>
	<c:when test="${albumListOrderedByTraffic != null}">
		<h2>ランキング</h2>
	</c:when>
	<c:when test="${albumListSortedByCategory != null}">
		<c:forEach var="albumList" items="${albumListSortedByCategory}" begin="0" end="0">
			<h2>${albumList.categoryName}</h2>
		</c:forEach>
	</c:when>
	<c:when test="${albumListSearchedByKeyword != null}">
		<h2>「${keyword}」の検索結果</h2>
	</c:when>
	<c:when test="${categoryList != null}">
		<h2>ジャンル</h2>
	</c:when>
</c:choose>

<!-- 新着一覧出力 -->
<c:forEach var="albumList"
	items="${albumListOrderedByDate}" begin="0" end="12">
	<a href="">
		<div>
			<img src="../image/album/${albumList.id}.jpg"
				alt="product image">
			<p>${albumList.name}</p>
			<br>
			<p>${albumList.artist}</p>
			<br>
		</div>
	</a>
</c:forEach>

<!-- ランキング一覧出力 -->
<c:forEach var="albumList"
	items="${albumListOrderedByTraffic}" begin="0" end="12">
	<a href="">
		<div>
			<img src="../image/album/${albumList.id}.jpg"
				alt="product image">
			<p>${albumList.name}</p>
			<br>
			<p>${albumList.artist}</p>
			<br>
		</div>
	</a>
</c:forEach>

<!-- カテゴリー別一覧出力 -->
<c:forEach var="albumList"
	items="${albumListSortedByCategory}" begin="0" end="12">
	<a href="">
		<div>
			<img src="../image/album/${albumList.id}.jpg"
				alt="product image">
			<p>${albumList.name}</p>
			<br>
			<p>${albumList.artist}</p>
			<br>
		</div>
	</a>
</c:forEach>

<!-- 検索結果一覧出力 -->
<c:forEach var="albumList"
	items="${albumListSearchedByKeyword}" begin="0" end="12">
	<a href="">
		<div>
			<img src="../image/album/${albumList.id}.jpg"
				alt="product image">
			<p>${albumList.name}</p>
			<br>
			<p>${albumList.artist}</p>
			<br>
		</div>
	</a>
</c:forEach>

<!-- カテゴリー一覧出力 -->
<c:forEach var="categoryList" items="${categoryList}">
	<a href="Search.action?categoryId=${categoryList.id}">
		<div>
			<img src="../image/category/${categoryList.id}.jpg"
				alt="category image">
			<p>${categoryList.name}</p>
		</div>
	</a>
</c:forEach>
<%@include file="footer.jsp"%>