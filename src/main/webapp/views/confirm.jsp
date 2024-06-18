<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="simple-header.jsp"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<h1>確認画面</h1>

<!-- 会員登録確認と購入確認 -->
<c:choose>

	<c:when test="${notTrueFinalRealuser != null}">
		<form action="SignupConfirm.action" method="post">
			ユーザーネーム:${notTrueFinalRealuser.username}<br>
			パスワード:${notTrueFinalRealuser.password}<br>
			苗字:${notTrueFinalRealuser.lastname}<br>
			名前:${notTrueFinalRealuser.firstname}<br>
			性別:${notTrueFinalRealuser.sex}<br>
			生年月日:${notTrueFinalRealuser.birthdate}<br>
			メールアドレス:${notTrueFinalRealuser.mailaddress}<br> <input
				type="submit" value="確定">
		</form>
		<a href="signup.jsp">修正</a>
	</c:when>

	<c:when test="${selectedSongsList != null}">
		<c:forEach var="cartItem" items="${selectedSongsList}">
			<p>${cartItem.albumName}</p>
			<p>${cartItem.artist}</p>
			<p>${cartItem.name}</p>
			<p>${cartItem.price}</p>
		</c:forEach>
		<form action="Confirm.action" method="post">
			<c:forEach var="cartItem" items="${selectedSongsList}">
				<input type="hidden" name="selectedSongs" value="${cartItem.id}">
			</c:forEach>
			<input type="submit" value="購入">
		</form>
		<a href="ShowCart.action">カートに戻る</a>
	</c:when>

	<c:when test="${newCategory != null && oldCategory != null}">
		<h2>変更前</h2>
		<img src="image/category/${oldCategory.imgName}" alt="categoryImage">
		<p>${oldCategory.categoryName}</p>
		<form action="views/Confirm.action" method="post">
			<h2>変更後</h2>
			<img src="image/category/${newCategory.imgName}" alt="categoryImage">
			<p>${newCategory.categoryName}</p>
			<input type="hidden" name="newCategoryId" value="${newCategory.categoryId}">
			<input type="hidden" name="newCategoryImgName" value="${newCategory.imgName}">
			<input type="hidden" name="newCategoryName" value="${newCategory.categoryName}">
			<input type="button" onclick="history.back()" value="修正">
			<input type="submit" value="確定">
		</form>
	</c:when>

	<c:otherwise>
		<p>存在しないページです</p>
	</c:otherwise>
</c:choose>

<%@include file="simple-footer.jsp"%>