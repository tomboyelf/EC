package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AdminDAO extends DAO {

//	カテゴリ情報変更
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
	
//	新規カテゴリ作成
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
	//	これ書くとしたら曲とアルバムも中間テーブルも一緒に削除するコードを書く必要がある
	//	めんどう

	//	public int deleteCategory(int categoryId) throws Exception {
	//		
	//	}

//	アルバム情報変更
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
	
//	新規アルバム作成
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
	
//	曲情報変更
	public int changeSong(String songName, String audioName, int price, int songId, int albumId)
			throws Exception {
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"update songs set song_name = ?, price = ?, album_id = ?, audio_name = ? where id = ?")) {
			st.setString(1, songName);
			st.setInt(2, price);
			st.setInt(3, songId);
			st.setString(4, audioName);
			st.setInt(5, albumId);
			int line = st.executeUpdate();
			return line;
		}
	}
	
//	新規曲作成
	public int createNewSong(String songName, String audioName, int price, int albumId)
			throws Exception {
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"insert into songs (song_name, audio_name, price, album_id) values (?, ?, ?, ?)")) {
			st.setString(1, songName);
			st.setString(2, audioName);
			st.setInt(3, price);
			st.setInt(4, albumId);
			int line = st.executeUpdate();
			return line;
		}
	}
	
//	顧客一覧取得
//	public int showAllUser(String songName, String audioName, int price, int albumId)
//			throws Exception {
//		
//	}
}
