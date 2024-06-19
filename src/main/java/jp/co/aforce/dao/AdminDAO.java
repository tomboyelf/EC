package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AdminDAO extends DAO {

	public int changeCategoryImgAndCategoryName(String imgName, String categoryName, int id) throws Exception {
		int line;
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"update categories set img_name = ?, category_name = ? where id = ?")) {
			st.setString(1, imgName);
			st.setString(2, categoryName);
			st.setInt(3, id);
			line = st.executeUpdate();
		}
		return line;
	}
	
	public int createNewCategory(String imgName, String categoryName) throws Exception {
		int line;
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"insert into categories (img_name, category_name) values(?, ?)")) {
			st.setString(1, imgName);
			st.setString(2, categoryName);
			line = st.executeUpdate();
		}
		return line;
	}

	//	カテゴリ削除
	//	これ書くとしたら曲とアルバムも一緒に削除するコードを書く必要がある
	//	めんどう

	//	public int deleteCategory(int categoryId) throws Exception {
	//		
	//	}

	public int changeAlbum(String imgName, String albumName, String artist, int categoryId, int albumId)
			throws Exception {
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"update albums set img_name = ?, album_name = ?, artist = ?, category_id = ? where id = ?")) {
			st.setString(1, imgName);
			st.setString(2, albumName);
			st.setString(3, artist);
			st.setInt(4, categoryId);
			st.setInt(5, albumId);
			int line = st.executeUpdate();
			return line;
		}
	}
	
	public int createNewAlbum(String imgName, String albumName, String artist, int categoryId)
			throws Exception {
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"insert into albums (img_name, album_name, artist, category_id) values (?, ?, ?, ?)")) {
			st.setString(1, imgName);
			st.setString(2, albumName);
			st.setString(3, artist);
			st.setInt(4, categoryId);
			int line = st.executeUpdate();
			return line;
		}
	}

	//	public int changeAlbumsCategory(int albumId, int categoryId) throws Exception {
	//		try (Connection con = getConnection();
	//				PreparedStatement st = con.prepareStatement(
	//						"update albums set category_id = ? where id = ?")) {
	//			st.setInt(1, categoryId);
	//			st.setInt(2, albumId);
	//			int line = st.executeUpdate();
	//			return line;
	//		}
	//	}

}
