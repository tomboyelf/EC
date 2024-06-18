<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="simple-header.jsp"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:choose>
	<c:when test="${optionId == 'profile'}">

		<h1>プロフィール設定</h1>
<form action="Signup.action?optionId=${optionId}" method="post">
			<label>ユーザーネーム</label> <input type="text" name="newUsername" required>
			<c:choose>
				<c:when test="${signupErrorMsg000 != null}">${signupErrorMsg000}</c:when>
				<c:when
					test="${signupErrorMsg000 != null && signupErrorMsg002 != null}">${signupErrorMsg000}</c:when>
				<c:otherwise>${signupErrorMsg002}</c:otherwise>
			</c:choose>
			<br> 性別 <select name="sex" required>
				<option value="男">男性</option>
				<option value="女">女性</option>
			</select><br> 生年月日： <select name="birth-year" required>
				<c:forEach var="y" begin="1900" end="2024">
					<option value="${y}">${y}年
				</c:forEach>
			</select> <select name="birth-month" required>
				<c:forEach var="m" begin="01" end="12">
					<option value="${m}">${m}月
				</c:forEach>
			</select> <select name="birth-day" required>
				<c:forEach var="d" begin="01" end="31">
					<option value="${d}">${d}日
				</c:forEach>
			</select><br> <input type="submit" value="変更">
		</form>
	</c:when>

	<c:when test="${optionId == 'password'}">
		<form action="Signup.action?optionId=${optionId}" method="post">
			<label>旧パスワード</label> <input type="password" name="password" required>
			<label>新パスワード</label> <input type="password" name="newPassword"
				required> <label>再入力</label> <input type="password"
				name="newPassword" required> <input type="submit" value="変更">
		</form>
	</c:when>
	<c:when test="${optionId == 'mailaddress'}">
		<form action="Signup.action?optionId=${optionId}" method="post">
			<label>旧メールアドレス</label> <input type="mailaddress" name="mailaddress"
				required> 
			<label>新メールアドレス</label> <input type="mailaddress"
				name="newMailaddress" required> <input type="submit"
				value="変更">
		</form>
	</c:when>
	<c:when test="${optionId == 'quit'}">
		<p>本当に退会しますか</p>
		<a href="Signup.action?optionId=${optionId}">退会</a>
	</c:when>
	<c:otherwise>
	${signupErrorMsg009}
	${signupErrorMsg007}
	<c:if test="${signupErrorMsg007 == null}">
			${signupErrorMsg000}
		<c:if test="${signupErrorMsg000 == null}">
			${signupErrorMsg002}
		</c:if>
		
			${loginErrorMsg001}
		<c:if test="${loginErrorMsg001 == null}">
			${signupErrorMsg003}
		</c:if>
		
			${signupErrorMsg008}
		<c:if test="${signupErrorMsg008 == null}">
			${signupErrorMsg001}
			<c:if test="${signupErrorMsg001 == null}">
		    	${signupErrorMsg006}
		    </c:if>
		</c:if>
	</c:if>
		<a href="Mypage.action">Mypageに戻る</a>
	</c:otherwise>
</c:choose>


<%@include file="simple-footer.jsp"%>