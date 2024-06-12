package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.Album;
import jp.co.aforce.beans.Category;

public class ProductDAO extends DAO {
	//	List<Integer> errorMessageList = new ArrayList<>();
	//	Message msg = new Message();

	//新着
	public List<Album> showAlbumOrderedByDate() throws Exception {
		List<Album> albumList = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"select * from albums order by created_at desc")) {
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					Album album = new Album();
					album.setId(rs.getInt("id"));
					album.setCategoryId(rs.getInt("category_id"));
					album.setName(rs.getString("album_name"));
					album.setArtist(rs.getString("artist"));
					album.setTraffic(rs.getInt("traffic"));
					Timestamp createdAt = rs.getTimestamp("created_at");
					album.setCreatedAt(createdAt);
					Timestamp updatedAt = rs.getTimestamp("updated_at");
					album.setCreatedAt(updatedAt);
					albumList.add(album);
				}
			}
		}
		return albumList;
	}

	//ランキング
	public List<Album> showAlbumOrderedByTraffic() throws Exception {
		List<Album> albumList = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"select * from albums order by traffic")) {
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					Album album = new Album();
					album.setId(rs.getInt("id"));
					album.setCategoryId(rs.getInt("category_id"));
					album.setName(rs.getString("album_name"));
					album.setArtist(rs.getString("artist"));
					album.setTraffic(rs.getInt("traffic"));
					Timestamp createdAt = rs.getTimestamp("created_at");
					album.setCreatedAt(createdAt);
					Timestamp updatedAt = rs.getTimestamp("updated_at");
					album.setCreatedAt(updatedAt);
					albumList.add(album);
				}
			}
		}
		return albumList;
	}

	//	カテゴリー別
	public List<Album> showAlbumSortedByCategory(int categoryId) throws Exception {
		List<Album> albumList = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"select * from album_category_view where category_ID = ?")) {
			st.setInt(1, categoryId);
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					Album album = new Album();
					album.setId(rs.getInt("album_id"));
					album.setCategoryId(rs.getInt("category_id"));
					album.setName(rs.getString("album_name"));
					album.setArtist(rs.getString("artist"));
					album.setTraffic(rs.getInt("traffic"));
					Timestamp createdAt = rs.getTimestamp("created_at");
					album.setCreatedAt(createdAt);
					Timestamp updatedAt = rs.getTimestamp("updated_at");
					album.setCreatedAt(updatedAt);
					album.setCategoryName(rs.getString("category_name"));
					albumList.add(album);
				}
			}
		}
		return albumList;
	}

	//	カテゴリ取得
	public List<Category> showCategory() throws Exception {
		List<Category> categoryList = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"select * from categories")) {
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					Category category = new Category();
					category.setId(rs.getInt("id"));
					category.setName(rs.getString("category_name"));
					Timestamp createdAt = rs.getTimestamp("created_at");
					category.setCreatedAt(createdAt);
					Timestamp updatedAt = rs.getTimestamp("updated_at");
					category.setCreatedAt(updatedAt);
					categoryList.add(category);
				}
			}
		}
		return categoryList;
	}

	public List<Album> showAlbumSearchedByKeyword(String keyword) throws Exception {
		List<Album> albumList = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"select * from album_category_category_view where album_name like ? or artist like ? or song_name like ? or category_name like ?")) {
			String placeHolder = '%' + keyword + '%';
			st.setString(1, placeHolder);
			st.setString(2, placeHolder);
			st.setString(3, placeHolder);
			st.setString(4, placeHolder);
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					Album album = new Album();
					album.setId(rs.getInt("album_id"));
					album.setCategoryId(rs.getInt("category_id"));
					album.setName(rs.getString("album_name"));
					album.setArtist(rs.getString("artist"));
					album.setTraffic(rs.getInt("traffic"));
					Timestamp createdAt = rs.getTimestamp("created_at");
					album.setCreatedAt(createdAt);
					Timestamp updatedAt = rs.getTimestamp("updated_at");
					album.setCreatedAt(updatedAt);
					albumList.add(album);
				}
			}
		}
		return albumList;
	}

}
