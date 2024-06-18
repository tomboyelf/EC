<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<%
List<Integer> purchaseHistoryList = (List<Integer>) session.getAttribute("purchaseHistoryList");
List<Master> purchaseHistoryInfo = new ArrayList<>();
ProductDAO dao = new ProductDAO();
for (Integer list : purchaseHistoryList) {
	Master master = dao.getMasterInfo(list);
	purchaseHistoryInfo.add(master);
}
pageContext.setAttribute("purchaseHistoryInfo", purchaseHistoryInfo);
%>

<h2>購入履歴</h2>
<p>${purchaseErrorMsg}</p>
<p>${completeMsg}</p>
<c:if test="${purchaseHistoryInfo != null}">
	<c:forEach var="purchaseHistoryInfo" items="${purchaseHistoryInfo}">
		<img src="../image/album/${purchaseHistoryInfo.albumId}.jpg" alt="product image">
		<p>${purchaseHistoryInfo.albumName}</p>
		<p>${purchaseHistoryInfo.artist}</p>
		<p>${purchaseHistoryInfo.songName}</p>
		<p>${purchaseHistoryInfo.price}</p>
		<%-- <p>${purchaseHistoryInfo.updatedAt}</p> --%>
	</c:forEach>
</c:if>
<a href="index.jsp">ホーム画面へ</a>

<%@include file="footer.jsp"%>