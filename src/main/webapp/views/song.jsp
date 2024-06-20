<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="jp.co.aforce.beans.Song"%>
<%@include file="header.jsp"%>

<%
String currentPage = "song.jsp";
session.setAttribute("currentPage", currentPage);
%>


<!-- アルバム情報が上に、曲が下に -->
<p>${purchaseErrorMsg}</p>
<p>${completeMsg}</p>
<c:choose>
	<c:when test="${songsInAlbum != null}">
		<c:forEach var="songList" items="${songsInAlbum}" begin="0"
			end="0">
			<img src="../image/album/${songList.albumId}.jpg" alt="product image">
			<h2>${songList.albumName}</h2>
			<p>${songList.artist}</p>
		</c:forEach>

		<c:forEach var="songList" items="${songsInAlbum}" begin="0"
			end="12">
			<div>
				<p>${songList.name}</p>
				<audio src="../audio/${songList.audioName}" controls preload="auto"></audio>
				<a href="CartAdd.action?addCartId=${songList.id}"> 
					<p>￥${songList.price}</p>
				</a>
			</div>
		</c:forEach>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>

<%@include file="footer.jsp"%>