<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<%
String currentPage = "cart.jsp";
session.setAttribute("currentPage", currentPage);

List<Integer> inCartList = (List<Integer>) session.getAttribute("inCartList");
List<Master> cartInfo = new ArrayList<>();
ProductDAO dao = new ProductDAO();
for (Integer list : inCartList) {
	Master master = dao.getMasterInfo(list);
	cartInfo.add(master);
}
pageContext.setAttribute("cartInfo", cartInfo);
%>
<p>${purchaseErrorMsg}</p>
<p>${completeMsg}</p>

<c:if test="${cartInfo.size() != 0}">
	<c:forEach var="cartItem" items="${cartInfo}">
		<form action="Delete.action?deleteId=cart" method="post">
			<input type="hidden" name="songId" value="${cartItem.songId}">
			<input type="submit" value="削除">
		</form>
		<img src="../image/album/${cartItem.albumId}.jpg" alt="product image">
		<p>${cartItem.albumName}</p>
		<p>${cartItem.artist}</p>
		<p>${cartItem.songName}</p>
		<p>${cartItem.price}</p>
		<form id="notNext">
			<input type="checkbox" name="selectedSongs"
				value="${cartItem.songId}" form="next" checked />
		</form>
	</c:forEach>
<form action="Purchase.action" method="post" id="next">
	<input type="submit" value="購入確認画面へ">
</form>
</c:if>

<%@include file="footer.jsp"%>