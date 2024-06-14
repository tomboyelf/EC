<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<!-- 新着一覧出力 -->
<h2>新着</h2>
<c:forEach var="albumList_date" items="${albumAndSingleList_date}"
	begin="0" end="8">
	<a href="SearchSong.action?searchSongId=${albumList_date.id}">
		<div>
			<img src="../image/album/${albumList_date.id}.jpg"
				alt="product image">
			<p>${albumList_date.name}</p>
			<br>
			<p>${albumList_date.artist}</p>
			<br>
		</div>
	</a>
</c:forEach>


<!-- ランキング一覧出力 -->
<h2>ランキング</h2>
<c:forEach var="albumList_traffic" items="${albumAndSingleList_traffic}"
	begin="0" end="8">
	<a href="SearchSong.action?searchSongId=${albumList_traffic.id}">
		<div>
			<img src="../image/album/${albumList_traffic.id}.jpg"
				alt="product image">
			<p>${albumList_traffic.name}</p>
			<br>
			<p>${albumList_traffic.artist}</p>
			<br>
		</div>
	</a>
</c:forEach>

<!-- 閲覧履歴出力 -->
<c:if test="${albumList_hisotry != null && user != null}">
	<h2>閲覧履歴</h2>
	<c:forEach var="albumList_hisotry" items="${albumList_hisotry}"
		begin="0" end="4">
		<a href="SearchSong.action?searchSongId=${albumList_hisotry.id}">
			<div>
				<img src="../image/album/${albumList_hisotry.id}.jpg"
					alt="product image">
				<p>${albumList_hisotry.name}</p>
				<br>
				<p>${albumList_hisotry.artist}</p>
				<br>
			</div>
		</a>
	</c:forEach>
</c:if>


<%@include file="footer.jsp"%>
