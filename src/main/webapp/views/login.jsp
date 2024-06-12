<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="html/simple-header.html" %>

<%@taglib prefix="c" uri="jakarta.tags.core"%>
		
		<p>${loginErrorMsg}</p>
		<form action = "Login.action" method = "post">
			ユーザー名<input type = "text" name = "username" value = "${username}" required><br>
			パスワード<input type = "password" name = "password" required><br> 
			<input type = "submit" value = "ログイン">
		</form>
		<p>
			<a href = "signup.jsp">会員登録が済んでいない人はこちら</a>
		</p>
		
<%@include file = "html/simple-footer.html" %>
