<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="simple-header.jsp"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:choose>
	<c:when test="${categoryList != null && categoryList.size() != 0}">
		<h2>カテゴリー一覧</h2>
		<c:forEach var="category" items="${categoryList}">
			<a href="Admin.action?categoryId=${category.id}">${category.name}</a>
		</c:forEach>
	</c:when>

	<c:when test="${albumList != null && albumList.size() != 0}">
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
				action="/ShoppingSite/UploadCategoryImgServlet">
				<input type="hidden" name="categoryId"
					value="${category.categoryId}"> <input type="file"
					name="file" required /><br> <input type="text" name="name"
					value="${category.categoryName}" required /><br> <input
					type="submit" value="変更を適用" />
			</form>
			<h2>${category.categoryName}のアルバム、シングル一覧</h2>
		</c:forEach>
		<form action="AdminChange.action" method="post" id="next">
			<c:forEach var="album" items="${albumListSortedByCategory}">
				<p>${album.name}</p>
				<input type="hidden" name="albumId" value="${album.name}">
				<select name="categoryOption">
					<option value="" form="next">変更先を選択</option>
					<c:forEach var="category" items="${categoryOption}">
						<option value="${category.id}" form="next">${category.name}</option>
					</c:forEach>
				</select>
			</c:forEach>
			<input type="submit" value="ジャンルを変更">
		</form>
	</c:when>

	<c:when
		test="${songListWithAlbumId != null && songListWithAlbumId.size() != 0}">
		<c:forEach var="album" items="${songListWithAlbumId}" begin="0"
			end="0">
			<img src="../image/album/${album.albumImgName}" alt="albumImage">
			<form method="post" enctype="multipart/form-data"
				action="/ShoppingSite/UploadAlbumImgServlet">
				<input type="hidden" name="albumId" value="${album.albumId}">
				<input type="file" name="file" required /><br> <input
					type="text" name="name" value="${album.albumName}" required /><br>
				<input type="text" name="artist" value="${album.artist}" required /><br>
				<select name="categoryOption">
					<option value="${album.categoryId}" form="next">変更先を選択</option>
					<c:forEach var="category" items="${categoryOption}">
						<input type="hidden" name="categoryName" value="${category.name}">
						<option value="${category.id}" form="next">${category.name}</option>
					</c:forEach>
				</select> <input type="submit" value="変更を適用" />
			</form>
			<h2>${album.albumName}の曲一覧</h2>
		</c:forEach>
		<c:forEach var="song" items="${songListWithAlbumId}">
			<p>${song.name}</p>
		</c:forEach>
	</c:when>
	<c:otherwise>
		${msg }
		<p>失敗</p>
	</c:otherwise>
</c:choose>

<%@include file="simple-footer.jsp"%>