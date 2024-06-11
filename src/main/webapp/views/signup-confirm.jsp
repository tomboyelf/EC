<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="html/simple-header.html" %>

<h1>確認画面</h1>
<form action="SignupConfirm.action" method="post">
	ユーザーネーム:${notTrueFinalRealuser.username}<br>
	パスワード:${notTrueFinalRealuser.password}<br>
	苗字:${notTrueFinalRealuser.lastname}<br>
	名前:${notTrueFinalRealuser.firstname}<br>
	性別:${notTrueFinalRealuser.sex}<br>
	生年月日:${notTrueFinalRealuser.birthdate}<br>
	メールアドレス:${notTrueFinalRealuser.mailaddress}<br>
	<input type="submit" value="確定">
</form>
<a href="signup.jsp">修正</a>

<%@include file="html/simple-footer.html" %>