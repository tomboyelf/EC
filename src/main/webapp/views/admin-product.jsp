<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="simple-header.jsp"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:choose>
	<c:when test="${admin != null}">
		<c:choose>
			<c:when test="${categoryOption!= null && categoryOption.size() != 0}">
				<h2>新規アルバム（シングル）登録</h2>
				<form method="POST" enctype="multipart/form-data"
					action="/ShoppingSite/UploadAlbumImgServlet">
					<p>画像</p>
					<input type="file" name="file" required />
					<p>アルバム名</p>
					<input type="text" name="albumName" required />
					<p>アーティスト</p>
					<input type="text" name="artist" required /> <select
						name="categoryOption">
						<c:forEach var="category" items="${categoryOption}">
							<option value="${category.id}" form="next">${category.name}</option>
						</c:forEach>
					</select> <input type="submit" value="登録" />
				</form>
				<c:if test="${albumList != null && albumList.size() != 0}">
					<h2>アルバム一覧</h2>
					<c:forEach var="album" items="${albumList}">
						<a href="Admin.action?albumId=${album.id}">${album.name}</a>
					</c:forEach>
				</c:if>
			</c:when>

			<c:when test="${albumOption!= null && albumOption.size() != 0}">
				<h2>新規曲登録</h2>
				<form method="POST" enctype="multipart/form-data"
					action="/ShoppingSite/UploadSongAudioServlet">
					<select name="albumOption">
						<c:forEach var="album" items="${albumOption}">
							<option value="${album.id}" form="next">${album.name}</option>
						</c:forEach>
					</select>
					<p>曲名</p>
					<input type="text" name="songName" required />
					<p>値段</p>
					<input type="number" name="price" required />
					<p>音源</p>
					<input type="file" name="file" required /> 
					<input type="submit" value="登録" />
				</form>
				<c:if test="${songList.size() != 0 && songList != null}">
					<h2>曲一覧</h2>
					<c:forEach var="song" items="${songList}">
						<a href="Admin.action?songId=${song.id}">${song.name}</a>
					</c:forEach>
				</c:if>
			</c:when>

			<c:otherwise>
				<h2>新規カテゴリ登録</h2>
				<form method="POST" enctype="multipart/form-data"
					action="/ShoppingSite/UploadCategoryImgServlet">
					<p>画像</p>
					<input type="file" name="file" required />
					<p>カテゴリ名</p>
					<input type="text" name="categoryName" value="${category.categoryName}" required /> 
					<input type="submit" value="登録" />
				</form>
				<h2>カテゴリー一覧</h2>
				<c:forEach var="category" items="${categoryList}">
					<a href="Admin.action?categoryId=${category.id}">${category.name}</a>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</c:when>

	<c:when test="${category != null}">
		<form action="Delete.action?deleteId=category" method="post">
			<input type="hidden" name="categoryId" value="${category.id}">
			<input type="submit" value="削除">
		</form>
		<img src="../image/category/${category.imgName}" alt="categoryImage">
		<form method="POST" enctype="multipart/form-data"
			action="/ShoppingSite/ChangeCategoryImgServlet">
			<input type="file" name="file" required /> 
			<input type="hidden" name="categoryId" value="${category.id}"> 
			<input type="submit" value="画像を変更" />
		</form>
		<form method="POST" action="/ShoppingSite/ChangeCategoryNameServlet">
			<input type="text" name="categoryName" value="${category.name}" required /> 
			<input type="hidden" name="categoryId" value="${category.id}"> 
			<input type="submit" value="名前を変更" />
		</form>
		<c:if
			test="${albumListSortedByCategory != null && albumListSortedByCategory.size() != 0}">
			<h2>${category.name}のアルバム、シングル一覧</h2>
			<c:forEach var="album" items="${albumListSortedByCategory}">
				<p>${album.name}</p>
			</c:forEach>
		</c:if>
	</c:when>

	<c:when test="${album != null && categoryOption != null}">
		<form action="Delete.action?deleteId=album" method="post">
			<input type="hidden" name="albumId" value="${album.id}"> 
			<input type="submit" value="削除">
		</form>
		<img src="../image/album/${album.albumImgName}" alt="albumImage">
		<form method="post" enctype="multipart/form-data"
			action="/ShoppingSite/ChangeAlbumImgServlet">
			<input type="file" name="file" required /> 
			<input type="hidden" name="albumId" value="${album.id}"> 
			<input type="submit" value="変更を適用" />
		</form>
		<form method="post" action="/ShoppingSite/ChangeAlbumNameServlet">
			<input type="text" name="name" value="${album.name}" required />
			<input type="hidden" name="albumId" value="${album.id}"> 
			<input type="submit" value="変更を適用" />
		</form>
		<form method="post" action="/ShoppingSite/ChangeAlbumNameServlet">
			<input type="text" name="artist" value="${album.artist}" required />
			<input type="hidden" name="albumId" value="${album.id}"> 
			<input type="submit" value="変更を適用" />
		</form>
		<form method="post" action="/ShoppingSite/ChangeAlbumNameServlet">
			<input type="hidden" name="albumId" value="${album.id}"> 
			<select name="categoryOption">
				<c:forEach var="category" items="${categoryOption}">
					<option value="${category.id}">${category.name}</option>
				</c:forEach>
			</select> 
			<input type="submit" value="変更を適用" />
		</form>
		<c:if
			test="${songListWithAlbumId != null && songListWithAlbumId.size() != 0}">
			<h2>${album.name}の曲一覧</h2>
			<c:forEach var="song" items="${songListWithAlbumId}">
				<p>${song.name}</p>
			</c:forEach>
		</c:if>
	</c:when>

	<c:when test="${song != null}">
		<form action="Delete.action?deleteId=song" method="post">
			<input type="hidden" name="songId" value="${song.id}"> 
			<input type="submit" value="削除">
		</form>
		<audio src="../audio/${song.audioName}" controls preload="auto"></audio>
		<form method="post" action="Admin.action?songChangeId=nameChange">
			<input type="text" name="name" value="${song.name}" required />
			<input type="hidden" name="oldSongId" value="${song.id}">
			<input type="submit" value="変更を適用" />
		</form>
		<form method="post" action="Admin.action?songChangeId=priceChange">
			<input type="number" name="price" value="${song.price}" required />
			<input type="hidden" name="oldSongId" value="${song.id}"> 
			<input type="submit" value="変更を適用" />
		</form>
		<form method="post" action="Admin.action?songChangeId=albumChange">
			<input type="hidden" name="oldSongId" value="${song.id}"> 
			<select name="albumOption">
				<c:forEach var="album" items="${albumOption}">
					<option value="${album.id}">${album.name}</option>
				</c:forEach>
			</select> 
			<input type="submit" value="変更を適用" />
		</form>
		<form method="post" enctype="multipart/form-data"
			action="/ShoppingSite/ChangeSongAudioServlet">
			<input type="file" name="file" required /> 
			<input type="hidden" name="oldSongId" value="${song.id}"> 
			<input type="submit" value="変更を適用" />
		</form>
	</c:when>
	<c:otherwise>
		${msg }
		<p>失敗</p>
	</c:otherwise>
</c:choose>

<%@include file="simple-footer.jsp"%>