<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<!-- 新着一覧出力 -->
<h2>新着</h2>
<c:forEach var="albumList"
	items="${albumListOrderedByTraffic}" begin="0" end="8">
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
<h2>ランキング</h2>
<c:forEach var="albumList"
	items="${albumListOrderedByDate}" begin="0" end="8">
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


<%@include file="footer.jsp"%>
