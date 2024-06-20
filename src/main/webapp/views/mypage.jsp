<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="simple-header.jsp"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%
String currentPage = "mypage.jsp";
session.setAttribute("currentPage", currentPage);
%>

<h1>MyPage</h1>
<h3>登録情報一覧</h3>
<p>ユーザーid：${user.id}</p>
<p>ユーザーネーム：${user.username}</p>
<p>苗字：${user.lastname}</p>
<p>氏名：${user.firstname}</p>
<p>性別：${user.sex}</p>
<p>生年月日：${user.birthdate}</p>
<p>メールアドレス：${user.mailaddress}</p>
<h2>アカウント設定</h2>
<a href="Mypage.action?optionId=profile">プロフィール設定</a>
<a href="Mypage.action?optionId=password">パスワード設定</a>
<a href="Mypage.action?optionId=mailaddress">メールアドレス設定</a>
<a href="Mypage.action?optionId=quit">退会手続き</a>
<h2>その他</h2>
<a href="">こまったときは</a>
<a href="">ストアからのお知らせ</a>
<a href="Mypage.action?optionId=purchasehistory">購入履歴</a>

<%@include file="simple-footer.jsp"%>