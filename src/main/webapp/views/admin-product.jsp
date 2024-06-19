<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="simple-header.jsp"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:choose>
	<c:when test="${categoryList != null && categoryList.size() != 0}">
		<h2>新規カテゴリ登録</h2>
		<form method="POST" enctype="multipart/form-data" action="/ShoppingSite/UploadCategoryImgServlet">
			<p>画像</p><input type="file" name="file" required /><br> 
			<p>カテゴリ名</p><input type="text" name="categoryName" value="${category.categoryName}" required /><br> 
			<input type="submit" value="登録" />
		</form>
		<h2>カテゴリー一覧</h2>
		<c:forEach var="category" items="${categoryList}">
			<a href="Admin.action?categoryId=${category.id}">${category.name}</a>
		</c:forEach>
	</c:when>

	<c:when test="${albumList != null && albumList.size() != 0}">
		<h2>新規アルバム（シングル）登録</h2>
		<form method="POST" enctype="multipart/form-data" action="/ShoppingSite/UploadAlbumImgServlet">
			<p>画像</p><input type="file" name="file" required /><br> 
			<p>アルバム名</p><input type="text" name="albumName" required /><br>
			<p>アーティスト</p><input type="text" name="artist" required /><br>
				<select name="categoryOption">
					<c:forEach var="category" items="${categoryOption}">
						<option value="${category.id}" form="next">${category.name}</option>
					</c:forEach>
				</select>
			<input type="submit" value="登録" />
		</form>
		<h2>アルバム一覧</h2>
		<c:forEach var="album" items="${albumList}">
			<a href="Admin.action?albumId=${album.id}">${album.name}</a>
		</c:forEach>
	</c:when>

	<c:when
		test="${albumListSortedByCategory != null && albumListSortedByCategory.size() != 0}">
		<c:forEach var="category" items="${albumListSortedByCategory}"
			begin="0" end="0">
			<img src="../image/category/${category.categoryImgName}"
				alt="categoryImage">
			<form method="POST" enctype="multipart/form-data"
				action="/ShoppingSite/ChangeCategoryImgServlet">
				<input type="file" name="file" required /><br> 
				<input type="hidden" name="categoryId" value="${category.categoryId}"> 
				<input type="submit" value="画像を変更" />
			</form>	
			<form method="POST" action="/ShoppingSite/ChangeCategoryNameServlet">	
				<input type="text" name="categoryName" value="${category.categoryName}" required /><br> 
				<input type="hidden" name="categoryId" value="${category.categoryId}">
				<input type="submit" value="名前を変更" />
			</form>
			<h2>${category.categoryName}のアルバム、シングル一覧</h2>
		</c:forEach>
			<c:forEach var="album" items="${albumListSortedByCategory}">
				<p>${album.name}</p>
			</c:forEach>
	</c:when>
	
	<c:when test="${albumListSortedByCategory.size() == 0}">
		<p>このカテゴリにはアルバムが登録されていません</p>
		<button type="button" onclick="history.back()">戻る</button>
	</c:when>

	<c:when
		test="${album != null && categoryOption != null}">
			<img src="../image/album/${album.albumImgName}" alt="albumImage">
			<form method="post" enctype="multipart/form-data"
				action="/ShoppingSite/ChangeAlbumImgServlet">
				<input type="file" name="file" required /><br> 
				<input type="hidden" name="albumId" value="${album.id}">
				<input type="submit" value="変更を適用" />
			</form>
			<form method="post" action="/ShoppingSite/ChangeAlbumNameServlet">
				<input type="text" name="name" value="${album.name}" required /><br>
				<input type="hidden" name="albumId" value="${album.id}">
				<input type="submit" value="変更を適用" />
			</form>
			<form method="post" action="/ShoppingSite/ChangeAlbumNameServlet">
				<input type="text" name="artist" value="${album.artist}" required /><br>
				<input type="hidden" name="albumId" value="${album.id}">
				<input type="submit" value="変更を適用" />
			</form>
			<form method="post" action="/ShoppingSite/ChangeAlbumNameServlet">
				<input type="hidden" name="albumId" value="${album.id}">
				<select name="categoryOption">
					<c:forEach var="category" items="${categoryOption}">
						<option value="${category.id}" form="next">${category.name}</option>
					</c:forEach>
				</select> 
				<input type="submit" value="変更を適用" />
			</form>
		<c:if test="${songListWithAlbumId.size() != 0}">
		<h2>${album.name}の曲一覧</h2>
		<c:forEach var="song" items="${songListWithAlbumId}">
			<p>${song.name}</p>
		</c:forEach>
		</c:if>
	</c:when>
	<c:otherwise>
		${msg }
		<p>失敗</p>
	</c:otherwise>
</c:choose>

<%@include file="simple-footer.jsp"%>