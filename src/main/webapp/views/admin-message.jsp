<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="simple-header.jsp"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:choose>
	<c:when test="${adminCompleteMsg != null}">
		<p>${adminCompleteMsg}</p>
		<a href="admin-index.jsp">管理者画面へ戻る</a>
	</c:when>
	
	<c:when test="${adminErrorMsg != null}">
		<p>${adminErrorMsg}</p>
		<a href="admin-index.jsp">管理者画面へ戻る</a>
	</c:when>
	
	<c:when test="${adminCompleteMsg005 != null || adminCompleteMsg006}">
		<p>${adminCompleteMsg005}</p>
		<p>${adminCompleteMsg006}</p>
		<a href="admin-index.jsp">管理者画面へ戻る</a>
	</c:when>

	<c:when test="${adminCompleteMsg007 != null}">
		<p>${adminCompleteMsg007}</p>
		<p>このカテゴリ、またはアルバムに含まれている情報を削除してからもう一度試してください</p>
		<a href="admin-index.jsp">管理者画面へ戻る</a>
	</c:when>
</c:choose>


<%@include file="simple-footer.jsp"%>