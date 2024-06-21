package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

	//	曲削除
	public void deleteSong(int songId) throws Exception {
		Connection con = getConnection();
		PreparedStatement songSt = null;
		PreparedStatement statusSt = null;
		try {
			//			トランザクション実装
			con.setAutoCommit(false);

			songSt = con.prepareStatement("delete from song_statuses where song_id=?");
			songSt.setInt(1, songId);
			songSt.executeUpdate();

			statusSt = con.prepareStatement("delete from songs where id=?");
			statusSt.setInt(1, songId);
			statusSt.executeUpdate();

			con.commit();
		} finally {
			songSt.close();
			statusSt.close();
			con.setAutoCommit(true);
			con.close();
		}
	}

	//	アルバム削除
	public void deleteAlbum(int albumId) throws Exception {

		Connection con = getConnection();
		PreparedStatement albumSt = null;
		PreparedStatement statusSt = null;
		try {
			//			トランザクション実装
			con.setAutoCommit(false);

			albumSt = con.prepareStatement("delete from album_statuses where album_id=?");
			albumSt.setInt(1, albumId);
			albumSt.executeUpdate();

			statusSt = con.prepareStatement("delete from albums where id=?");
			statusSt.setInt(1, albumId);
			statusSt.executeUpdate();

			con.commit();
		} finally {
			albumSt.close();
			statusSt.close();
			con.setAutoCommit(true);
			con.close();
		}

	}

	//	カテゴリ削除
	public void deleteCategory(int categoryId) throws Exception {
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"DELETE FROM categories WHERE id = ?")) {
			st.setInt(1, categoryId);
			st.executeUpdate();
		}
	}

	//	企んでる
	public List<Integer> getSongIdForChart(Timestamp previousDate, Timestamp currentDate) throws Exception {
		List<Integer> songIdList = new ArrayList();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"SELECT * FROM SONG_STATUSES WHERE status = 2 and UPDATED_AT BETWEEN '2024-06-15 00:00:00' AND '2024-06-15 23:59:59';")) {
			st.setTimestamp(1, previousDate);
			st.setTimestamp(2, currentDate);
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					songIdList.add(rs.getInt("id"));
				}
			}
		}
		return songIdList;
	}

}
