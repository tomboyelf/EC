<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<%
List<Integer> purchaseHistoryList = (List<Integer>) session.getAttribute("purchaseHistoryList");
List<Song> songList = new ArrayList<>();
ProductDAO dao = new ProductDAO();
for (Integer songId : purchaseHistoryList) {
	Song song = dao.getSpecificSong(songId);
	songList.add(song);
}
pageContext.setAttribute("songList", songList);
%>

<c:if test="${songList != null}">
	<c:forEach var="songList" items="${songList}">
		<p>${songList.name}</p>
		<p>${songList.audioName}</p>
		<a href="../audio/${songList.audioName}" download="${songList.audioName}">こちらからダウンロード</a>
	</c:forEach>
</c:if>

<%@include file="footer.jsp"%>