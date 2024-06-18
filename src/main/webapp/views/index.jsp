<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<!-- 新着一覧出力 -->
<h2>新着</h2>
<c:forEach var="albumListDate" items="${albumAndSingleListDate}"
	begin="0" end="8">
	<a href="SearchSong.action?searchSongId=${albumListDate.id}">
		<div>
			<img src="../image/album/${albumListDate.id}.jpg"
				alt="product image">
			<p>${albumListDate.name}</p>
			<br>
			<p>${albumListDate.artist}</p>
			<br>
		</div>
	</a>
</c:forEach>


<!-- ランキング一覧出力 -->
<h2>ランキング</h2>
<c:forEach var="albumListTraffic" items="${albumAndSingleListTraffic}"
	begin="0" end="8">
	<a href="SearchSong.action?searchSongId=${albumListTraffic.id}">
		<div>
			<img src="../image/album/${albumListTraffic.id}.jpg"
				alt="product image">
			<p>${albumListTraffic.name}</p>
			<br>
			<p>${albumListTraffic.artist}</p>
			<br>
		</div>
	</a>
</c:forEach>

<!-- 閲覧履歴出力 -->
<c:if test="${albumListHisotry != null && user != null}">
	<h2>閲覧履歴</h2>
	<c:forEach var="albumListHisotry" items="${albumListHisotry}"
		begin="0" end="4">
		<a href="SearchSong.action?searchSongId=${albumListHisotry.id}">
			<div>
				<img src="../image/album/${albumListHisotry.id}.jpg"
					alt="product image">
				<p>${albumListHisotry.name}</p>
				<br>
				<p>${albumListHisotry.artist}</p>
				<br>
			</div>
		</a>
	</c:forEach>
</c:if>


<%@include file="footer.jsp"%>
