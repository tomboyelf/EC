<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>	
<%@ page import="java.util.List"%>
<%@ page import="jp.co.aforce.beans.Master"%>
<%@ page import="jp.co.aforce.beans.Song"%>
<%@ page import="jp.co.aforce.dao.ProductDAO"%>

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

<form action="SearchAlbum.action" method="post">
	<input type="text" name="keyword">
	<input type="submit" value="検索">
</form>

<c:if test="${user.id == 1}">
	<p>
		<a href="/ShoppingSite/views/admin-index.jsp">管理者画面へ</a>
	</p>
</c:if>



<c:choose>
	<c:when test="${user != null}">
		<p>${user.username}さんでログイン中</p>
		<p>
			<a href="ShowCart.action">カートを見る</a>
		</p>
		<p>
			<a href="Mypage.action">Myページへ</a>
		</p>
		<p>
			<a href="ShowPurchaseHistory.action">購入履歴を見る</a>
		</p>
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
		<li><a href="SearchAlbum.action?searchId=orderedByDate">新着</a>
		<li><a href="SearchAlbum.action?searchId=orderedByTraffic">ランキング</a>
		<li><a href="SearchAlbum.action?searchId=categoryPage">ジャンル</a>
	</ul>
</nav>

<p>ここまでヘッダー</p>
<hr>