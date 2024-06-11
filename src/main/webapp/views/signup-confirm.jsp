<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="html/simple-header.html" %>

<h1>確認画面</h1>
<form action="SignupConfirm.action" method="post">
	ユーザーネーム:${user.username }<br>
	パスワード:${user.password }<br>
	苗字:${user.lastname }<br>
	名前:${user.firstname }<br>
	性別:${user.sex }<br>
	生年月日:${user.birthdate }<br>
	メールアドレス:${user.mailaddress }<br>
	<input type="submit" value="登録">
</form>

<%@include file="html/simple-footer.html" %>