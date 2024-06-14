<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<!-- アルバム情報が上に、曲が下に -->
<c:choose>
	<c:when test="${songList_albumId != null}">
		<c:forEach var="songList" items="${songList_albumId}" begin="0"
			end="0">
			<img src="../image/album/${songList.albumId}.jpg" alt="product image">
			<h2>${songList.albumName}</h2>
			<p>${songList.artist}</p>
		</c:forEach>

		<c:forEach var="songList" items="${songList_albumId}" begin="0"
			end="12">
			<div>
				<p>${songList.name}</p>
				<a href="CartAdd.action?addCartId=${songList.id}">
					<p>￥${songList.price}</p>
				</a>
			</div>
		</c:forEach>
	</c:when>
</c:choose>

<%@include file="footer.jsp"%>