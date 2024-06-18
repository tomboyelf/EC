<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>

<c:choose>
	<c:when test="${albumListDate != null}">
		<h2>新着</h2>
	</c:when>
	<c:when test="${albumListTraffic != null}">
		<h2>ランキング</h2>
	</c:when>
	<c:when test="${albumListInCategoryOrderedByDate != null}">
		<c:forEach var="albumList" items="${albumListInCategoryOrderedByDate}"
			begin="0" end="0">
			<h2>${albumList.categoryName}</h2>
		</c:forEach>
	</c:when>
	<c:when test="${songListKeyword != null}">
		<h2>「${keyword}」の検索結果</h2>
	</c:when>
	<c:when test="${categoryList != null}">
		<h2>ジャンル</h2>
	</c:when>
</c:choose>

<!-- 新着一覧出力 -->
<!-- 今週のシングル -->
<c:if
	test="${singleListWeekly !=  null && singleListWeekly.size() != 0}">
	<h3>今週のシングル</h3>
	<c:forEach var="singleList" items="${singleListWeekly}" begin="0"
		end="12">
		<a href="SearchSong.action?searchSongId=${singleList.id}">
			<div>
				<img src="../image/album/${singleList.id}.jpg" alt="product image">
				<p>${singleList.name}</p>
				<br>
				<p>${singleList.artist}</p>
				<br>
			</div>
		</a>
	</c:forEach>
</c:if>

<!-- 今週のアルバム -->
<c:if test="${albumListWeekly !=  null && albumListWeekly.size() != 0}">
	<h3>今週のアルバム</h3>
	<c:forEach var="albumList" items="${albumListWeekly}" begin="0"
		end="12">
		<a href="SearchSong.action?searchSongId=${albumList.id}">
			<div>
				<img src="../image/album/${albumList.id}.jpg" alt="product image">
				<p>${albumList.name}</p>
				<br>
				<p>${albumList.artist}</p>
				<br>
			</div>
		</a>
	</c:forEach>
</c:if>

<!-- 先週発売のアルバムも追加予定 -->


<!-- ランキング一覧出力 -->
<!-- 今日発売の人気アルバム -->
<c:if
	test="${albumTrafficListDaily !=  null && albumTrafficListDaily.size() != 0}">
	<h3>今日の人気アルバム</h3>
	<c:forEach var="albumList" items="${albumTrafficListDaily}" begin="0"
		end="12">
		<a href="SearchSong.action?searchSongId=${albumList.id}">
			<div>
				<img src="../image/album/${albumList.id}.jpg" alt="product image">
				<p>${albumList.name}</p>
				<br>
				<p>${albumList.artist}</p>
				<br>
			</div>
		</a>
	</c:forEach>
</c:if>

<!-- 今週発売の人気アルバム -->
<c:if
	test="${albumTrafficListWeekly !=  null && albumTrafficListWeekly.size() != 0}">
	<h3>今週の人気アルバム</h3>
	<c:forEach var="albumList" items="${albumTrafficListWeekly}" begin="0"
		end="12">
		<a href="SearchSong.action?searchSongId=${albumList.id}">
			<div>
				<img src="../image/album/${albumList.id}.jpg" alt="product image">
				<p>${albumList.name}</p>
				<br>
				<p>${albumList.artist}</p>
				<br>
			</div>
		</a>
	</c:forEach>
</c:if>

<!-- 今月発売の人気アルバム -->
<c:if
	test="${albumTrafficListMonthly !=  null && albumTrafficListMonthly.size() != 0}">
	<h3>今月の人気アルバム</h3>
	<c:forEach var="albumList" items="${albumTrafficListMonthly}" begin="0"
		end="12">
		<a href="SearchSong.action?searchSongId=${albumList.id}">
			<div>
				<img src="../image/album/${albumList.id}.jpg" alt="product image">
				<p>${albumList.name}</p>
				<br>
				<p>${albumList.artist}</p>
				<br>
			</div>
		</a>
	</c:forEach>
</c:if>

<!-- 今日発売の人気シングル -->
<c:if
	test="${singleTrafficListDayly !=  null && singleTrafficListDayly.size() != 0}">
	<h3>今日の人気シングル</h3>
	<c:forEach var="albumList" items="${singleTrafficListDayly}" begin="0"
		end="12">
		<a href="SearchSong.action?searchSongId=${albumList.id}">
			<div>
				<img src="../image/album/${albumList.id}.jpg" alt="product image">
				<p>${albumList.name}</p>
				<br>
				<p>${albumList.artist}</p>
				<br>
			</div>
		</a>
	</c:forEach>
</c:if>

<!-- 今週発売の人気シングル -->
<c:if
	test="${singleTrafficListWeekly !=  null && singleTrafficListWeekly.size() != 0}">
	<h3>今週の人気シングル</h3>
	<c:forEach var="albumList" items="${singleTrafficListWeekly}" begin="0"
		end="12">
		<a href="SearchSong.action?searchSongId=${albumList.id}">
			<div>
				<img src="../image/album/${albumList.id}.jpg" alt="product image">
				<p>${albumList.name}</p>
				<br>
				<p>${albumList.artist}</p>
				<br>
			</div>
		</a>
	</c:forEach>
</c:if>

<!-- 今月発売の人気シングル -->
<c:if
	test="${singleTrafficListMonthly !=  null && singleTrafficListMonthly.size() != 0}">
	<h3>今月の人気シングル</h3>
	<c:forEach var="albumList" items="${singleTrafficListMonthly}"
		begin="0" end="12">
		<a href="SearchSong.action?searchSongId=${albumList.id}">
			<div>
				<img src="../image/album/${albumList.id}.jpg" alt="product image">
				<p>${albumList.name}</p>
				<br>
				<p>${albumList.artist}</p>
				<br>
			</div>
		</a>
	</c:forEach>
</c:if>

<!-- カテゴリー別一覧出力 -->
<!-- カテゴリー別新着 -->
<c:if
	test="${albumListInCategoryOrderedByDate !=  null && albumListInCategoryOrderedByDate.size() != 0}">
	<h3>新着</h3>
	<c:forEach var="albumList" items="${albumListInCategoryOrderedByDate}"
		begin="0" end="12">
		<a href="SearchSong.action?searchSongId=${albumList.id}">
			<div>
				<img src="../image/album/${albumList.id}.jpg" alt="product image">
				<p>${albumList.name}</p>
				<br>
				<p>${albumList.artist}</p>
				<br>
			</div>
		</a>
	</c:forEach>
</c:if>

<!-- カテゴリー別ランキング -->
<c:if
	test="${albumListInCategoryOrderedByTraffic !=  null && albumListInCategoryOrderedByTraffic.size() != 0}">
	<h3>ランキング</h3>
	<c:forEach var="albumList"
		items="${albumListInCategoryOrderedByTraffic}" begin="0" end="12">
		<a href="SearchSong.action?searchSongId=${albumList.id}">
			<div>
				<img src="../image/album/${albumList.id}.jpg" alt="product image">
				<p>${albumList.name}</p>
				<br>
				<p>${albumList.artist}</p>
				<br>
			</div>
		</a>
	</c:forEach>
</c:if>

<!-- 検索結果一覧出力 -->
<c:if test="${songListKeyword !=  null && songListKeyword.size() != 0}">
	<c:forEach var="songList" items="${songListKeyword}" begin="0" end="12">
		<a href="SearchSong.action?searchSongId=${songList.albumId}">
			<div>
				<img src="../image/album/${songList.albumId}.jpg"
					alt="product image">
				<p>${songList.name}</p>
				<p>${songList.albumName}</p>
				<br>
				<p>${songList.artist}</p>
				<br>
			</div>
		</a>
	</c:forEach>
</c:if>

<!-- カテゴリー一覧出力 -->
<c:if test="${categoryList !=  null && categoryList.size() != 0}">
	<c:forEach var="categoryList" items="${categoryList}">
		<a href="SearchAlbum.action?categoryId=${categoryList.id}">
			<div>
				<img src="../image/category/${categoryList.imgName}"
					alt="category image">
				<p>${categoryList.name}</p>
			</div>
		</a>
	</c:forEach>
</c:if>
<%@include file="footer.jsp"%>