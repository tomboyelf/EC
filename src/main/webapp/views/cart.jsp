<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<form action="Purchase.action" method="post">
	<c:if test="${cartInfo != null}">
		<c:forEach var="cartItem" items="${cartInfo}">
		<input type="checkbox" name="selectedSongs" value="${cartItem.songId}" checked />
			<img src="../image/album/${cartItem.albumId}.jpg" alt="product image">
			<p>${cartItem.albumName}</p>
			<p>${cartItem.artist}</p>
			<p>${cartItem.songName}</p>
			<p>${cartItem.price}</p>
		</c:forEach>
	<input type="submit" value="購入確認画面へ">
	</c:if>
</form>

<c:if test="${cartInfo == null}">
<p>カートが空です</p>
</c:if>

<%@include file="footer.jsp"%>