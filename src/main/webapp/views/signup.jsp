<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="html/simple-header.html" %>

<%@taglib prefix="c" uri="jakarta.tags.core"%>

<h1>登録画面</h1>
<form action="Signup.action" method="post">
	<p>ユーザーネーム：英数字のみかつ5文字以上</p>
	ユーザーネーム<input type="text" name="username" required>
	<c:choose>
		<c:when test="${signupErrorMsg000 !=null}">${signupErrorMsg000 }</c:when>
		<c:when test="${signupErrorMsg000 !=null && signupErrorMsg002 !=null}">${signupErrorMsg000 }</c:when>
		<c:otherwise>${signupErrorMsg002 }</c:otherwise>
	</c:choose>
	<br>
	
	<p>パスワード：英小文字大文字数字全部必要かつ5文字以上</p>
	パスワード<input type="password" name="password" required>${signupErrorMsg003 }<br>
	
	<p>苗字および氏名：日本語かつ1文字以上</p>
	苗字<input type="text" name="lastname" required>${signupErrorMsg004 }<br>
	名前<input type="text" name="firstname" required>${signupErrorMsg005 }<br>
	
	性別
	<select name="sex" required>
		<option value="男">男性</option>
		<option value="女">女性</option>
	</select><br>
	
	生年月日：
	<select name="birth-year" required>
	<c:forEach var="y" begin="1900" end="2024"><option value="${y }">${y }年</c:forEach>
	</select>
	<select name="birth-month" required>
	<c:forEach var="m" begin="01" end="12"><option value="${m }">${m }月</c:forEach>
	</select>
	<select name="birth-day" required>
	<c:forEach var="d" begin="01" end="31"><option value="${d }">${d }日</c:forEach>
	</select><br>
	
	<p>メールアドレス：＠が必要</p>
	メールアドレス<input type="text" name="mailaddress" required>${signupErrorMsg006 }<br>
	<input type="submit" value="確認画面へ">
</form>

<%@include file="html/simple-footer.html" %>