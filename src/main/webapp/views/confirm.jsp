<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="simple-header.jsp"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<h1>確認画面</h1>

<!-- 会員登録確認と購入確認 -->

<c:choose>
	<c:when test="${deleteId != null}">
		<c:choose>
			<c:when test="${deleteId == 'song'}">
				<p>本当に削除しますか</p>
				<form action="Delete.action?deleteId=deleteSong" method="post">
					<input type="hidden" name="songId" value="${songId}">
					<input type="button" onclick="history.back()" value="やめる">
					<input type="submit" value="削除">
				</form>
			</c:when>
			<c:when test="${deleteId == 'album'}">
				<p>本当に削除しますか</p>
				<form action="Delete.action?deleteId=deleteAlbum" method="post">
					<input type="hidden" name="albumId" value="${albumId}">
					<input type="button" onclick="history.back()" value="やめる">
					<input type="submit" value="削除">
				</form>
			</c:when>
			<c:when test="${deleteId == 'category'}">
				<p>本当に削除しますか</p>
				<form action="Delete.action?deleteId=deleteCategory" method="post">
					<input type="hidden" name="categoryId" value="${categoryId}">
					${categoryId}
					<input type="button" onclick="history.back()" value="やめる">
					<input type="submit" value="削除">
				</form>
			</c:when>
		</c:choose>
	</c:when>

	<c:when test="${adminCompleteMsg005 != null || adminCompleteMsg006}">
		<p>${adminCompleteMsg005}</p>
		<p>${adminCompleteMsg006}</p>
		<a href="admin-index.jsp">管理者画面へ戻る</a>
	</c:when>

	<c:when test="${adminCompleteMsg007 != null}">
		<p>${adminCompleteMsg007}</p>
		<p>このカテゴリ、またはアルバムに含まれている情報を削除してからもう一度試してください</p>
		<a href="admin-index.jsp">管理者画面へ戻る</a>
	</c:when>
	
	<c:when test="${confirmUser != null}">
		<form action="SignupConfirm.action" method="post">
			<p>ユーザーネーム:${confirmUser.username}</p><br>
			<p>パスワード:${confirmUser.password}</p><br>
			<p>苗字:${confirmUser.lastname}</p><br>
			<p>名前:${confirmUser.firstname}</p><br>
			<p>性別:${confirmUser.sex}</p><br>
			<p>生年月日:${confirmUser.birthdate}</p><br>
			<p>メールアドレス:${confirmUser.mailaddress}</p><br> 
			<input type="hidden" name="confirmUsername" value="${confirmUser.username}">
			<input type="hidden" name="confirmPassword" value="${confirmUser.password}">
			<input type="hidden" name="confirmLastname" value="${confirmUser.lastname}">
			<input type="hidden" name="confirmFirstname" value="${confirmUser.firstname}">
			<input type="hidden" name="confirmSex" value="${confirmUser.sex}">
			<input type="hidden" name="confirmBirthdate" value="${confirmUser.birthdate}">
			<input type="hidden" name="confirmMailaddress" value="${confirmUser.mailaddress}">
			<input type="button" onclick="history.back()" value="修正">
			<input type="submit" value="確定">
		</form>
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
		<img src="image/category/${oldCategory.imgName}" alt="categoryImage" rel="preload">
		<p>${oldCategory.name}</p>
		<form action="views/Confirm.action" method="post">
			<h2>変更後</h2>
			<img src="image/category/${newCategory.imgName}" alt="categoryImage" rel="preload">
			<p>${newCategory.name}</p>
			<input type="hidden" name="newCategoryId" value="${newCategory.id}">
			<input type="hidden" name="newCategoryImgName" value="${newCategory.imgName}">
			<input type="hidden" name="newCategoryName" value="${newCategory.name}">
			<input type="button" onclick="history.back()" value="修正">
			<input type="submit" value="確定">
		</form>
	</c:when>
	
	<c:when test="${newCategory != null && oldCategory == null}">
		<form action="views/Confirm.action" method="post">
			<img src="image/category/${newCategory.imgName}" alt="categoryImage" rel="preload">
			<p>${newCategory.name}</p>
			<input type="hidden" name="newCategoryImgName" value="${newCategory.imgName}">
			<input type="hidden" name="newCategoryName" value="${newCategory.name}">
			<input type="button" onclick="history.back()" value="修正">
			<input type="submit" value="確定">
		</form>
	</c:when>
	
	<c:when test="${newAlbum != null && oldAlbum != null}">
		<h2>変更前</h2> 
		<img src="image/album/${oldAlbum.albumImgName}" alt="albumImage" rel="preload">
		<p>${oldAlbum.name}</p>
		<p>${oldAlbum.artist}</p>
		<p>${oldAlbum.categoryName}</p>
		<form action="views/Confirm.action" method="post">
			<h2>変更後</h2>
			<img src="image/album/${newAlbum.albumImgName}" alt="albumImage" rel="preload">
			<p>${newAlbum.name}</p>
			<p>${newAlbum.artist}</p>
			<p>${newAlbum.categoryName}</p>
			<input type="hidden" name="newAlbumId" value="${oldAlbum.id}">
			<input type="hidden" name="newAlbumImgName" value="${newAlbum.albumImgName}">
			<input type="hidden" name="newAlbumName" value="${newAlbum.name}">
			<input type="hidden" name="newAlbumArtist" value="${newAlbum.artist}">
			<input type="hidden" name="newAlbumCategory" value="${newAlbum.categoryId}">
			<input type="button" onclick="history.back()" value="修正">
			<input type="submit" value="確定">
		</form>
	</c:when>
	
	<c:when test="${newSong != null && oldSong == null}">
		<form action="views/Confirm.action" method="post">
			<p>${newSong.albumName}</p>
			<p>${newSong.name}</p>
			<p>${newSong.price}</p>
			<audio src="audio/${newSong.audioName}" controls preload="auto"></audio>
			<input type="hidden" name="newSongAudio" value="${newSong.audioName}">
			<input type="hidden" name="newSongName" value="${newSong.name}">
			<input type="hidden" name="newSongPrice" value="${newSong.price}">
			<input type="hidden" name="newSongAlbum" value="${newSong.albumId}">
			<input type="button" onclick="history.back()" value="修正">
			<input type="submit" value="確定">
		</form>
	</c:when>
	
	<c:when test="${newSong != null && oldSong != null}">
		<h2>変更前</h2>
		<p>${oldSong.name}</p>
		<p>${oldSong.price}</p>
		<p>${oldSong.albumName}</p>
		<audio src="audio/${oldSong.audioName}" controls preload="auto"></audio>
		<form action="Confirm.action" method="post">
			<h2>変更後</h2>
			<p>${newSong.name}</p>
			<p>${newSong.price}</p>
			<p>${newSong.albumName}</p>
			<audio src="audio/${newSong.audioName}" controls preload="auto"></audio>
			<input type="hidden" name="newSongId" value="${oldSong.id}">
			<input type="hidden" name="newSongName" value="${newSong.name}">
			<input type="hidden" name="newSongPrice" value="${newSong.price}">
			<input type="hidden" name="newSongAlbum" value="${newSong.albumId}">
			<input type="hidden" name="newSongAudio" value="${newSong.audioName}">
			<input type="button" onclick="history.back()" value="修正">
			<input type="submit" value="確定">
		</form>
	</c:when>
	
	<c:otherwise>
		<p>存在しないページです</p>
	</c:otherwise>
</c:choose>

<%@include file="simple-footer.jsp"%>