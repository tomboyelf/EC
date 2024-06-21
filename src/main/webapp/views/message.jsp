<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="simple-header.jsp"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:choose>
	<c:when test="${completeMsg003 != null}">
		<p>${completeMsg003}</p>
		<a href="download.jsp">ダウンロードページへ</a>
	</c:when>
	<c:when test="${completeMsg != null}">
		<p>${completeMsg}</p>
		<a href="index.jsp">ホームへ戻る</a>
	</c:when>
	
	<c:when test="${errorMsg != null}">
		<p>${errorMsg}</p>
		<a href="index.jsp">ホームへ戻る</a>
	</c:when>
	
</c:choose>


<%@include file="simple-footer.jsp"%>