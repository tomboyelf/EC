<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ShoppingSite</title>
</head>
<body>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<p>
	<a href="/ShoppingSite/views/index.jsp">サイトのロゴ</a>
</p>

<form action=".action" method="post">
	<input type="text">
	<input type="submit" value="検索">
</form>


<!-- 管理者アカウントでログインした際に、ヘッダーに管理者画面へのリンクを張る場合のコード
el文の中とパスを書き換えて使用 -->
<%-- <c:if test="${user.id == 1}">
	<p>
		<a href="/ShoppingSite/jsp/views/.jsp">管理者画面へ</a>
	</p>
</c:if> --%>



<!-- ログインしているかどうかをjstlを用いて判断 -->
<c:choose>
	<c:when test="${user != null}">
		<p>${user.username}さんでログイン中</p>
		<p>
			<a href="Logout.action">ログアウト</a>
		</p>
	</c:when>
	<c:when test="${user == null}">
	<p>${loginError}</p>
		<p>
			<a href="/ShoppingSite/views/login.jsp">ログイン</a>
		</p>
		<p>
			<a href="/ShoppingSite/views/signup.jsp">会員登録</a>
		</p>
	</c:when>
	<c:otherwise> 
		<p>ここへ分岐することはないはず。書かないと表示されないため仕方なく。</p>	
	</c:otherwise>
</c:choose>


<nav>
	<ul>
		<li><a href=".action">新着</a>
		<li><a href=".action">ランキング</a>
		<li><a href=".action">ジャンル</a>
	</ul>
</nav>

<p>ここまでヘッダー</p>
<hr>