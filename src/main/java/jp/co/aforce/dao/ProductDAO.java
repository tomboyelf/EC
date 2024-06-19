package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.aforce.beans.Album;
import jp.co.aforce.beans.Category;
import jp.co.aforce.beans.Master;
import jp.co.aforce.beans.Song;

public class ProductDAO extends DAO {
	
//	指定カテゴリ取得
	public Category getSpecificCategory(int categoryId) throws Exception {
		Category category = new Category();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"select * from categories where id = ?")) {
			st.setInt(1, categoryId);
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					category.setId(rs.getInt("id"));
					category.setName(rs.getString("category_name"));
					category.setImgName(rs.getString("img_name"));
				}
			}
		}
		return category;
	}
	
//	指定アルバム取得
	public Album getSpecificAlbum(int albumId) throws Exception {
		Album album = new Album();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"select * from album_category_view where album_id = ?")) {
			st.setInt(1, albumId);
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					album.setId(rs.getInt("album_id"));
					album.setAlbumImgName(rs.getString("album_img_name"));
					album.setCategoryId(rs.getInt("category_id"));
					album.setCategoryName(rs.getString("category_name"));
					album.setName(rs.getString("album_name"));
					album.setArtist(rs.getString("artist"));
				}
			}
		}
		return album;
	}

	//新着全部
	public List<Album> getAlbumAndSingleOrderedByDate() throws Exception {
		List<Album> list = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"SELECT id, category_id, album_name, artist, created_at FROM albums ORDER BY CREATED_AT desc")) {
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					Album album = new Album();
					album.setId(rs.getInt("id"));
					album.setCategoryId(rs.getInt("category_id"));
					album.setName(rs.getString("album_name"));
					album.setArtist(rs.getString("artist"));
					list.add(album);
				}
			}
		}
		return list;
	}

	//新着シングル
	public List<Album> getSinglesOrderedByDate() throws Exception {
		List<Album> singleList = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"SELECT album_id, album_name, artist, album_created_at FROM master_view GROUP BY album_id HAVING COUNT(*) < 4 ORDER BY ALBUM_CREATED_AT desc")) {
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					Album album = new Album();
					album.setId(rs.getInt("album_id"));
					album.setName(rs.getString("album_name"));
					album.setArtist(rs.getString("artist"));
					Timestamp createdAt = rs.getTimestamp("album_created_at");
					album.setCreatedAt(createdAt);
					singleList.add(album);
				}
			}
		}
		return singleList;
	}

	//新着アルバム
	public List<Album> getAlbumsOrderedByDate() throws Exception {
		List<Album> albumList = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"SELECT album_id, album_name, artist, album_created_at FROM master_view GROUP BY album_id HAVING COUNT(*) >= 4 ORDER BY ALBUM_CREATED_AT desc")) {
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					Album album = new Album();
					album.setId(rs.getInt("album_id"));
					album.setName(rs.getString("album_name"));
					album.setArtist(rs.getString("artist"));
					Timestamp createdAt = rs.getTimestamp("album_created_at");
					album.setCreatedAt(createdAt);
					albumList.add(album);
				}
			}
		}
		return albumList;
	}

	//ランキング全部
	public List<Album> getAlbumAndSingleOrderedByTraffic() throws Exception {
		List<Album> list = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"SELECT id, album_name, artist, created_at FROM albums ORDER BY traffic desc")) {
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					Album album = new Album();
					album.setId(rs.getInt("id"));
					album.setName(rs.getString("album_name"));
					album.setArtist(rs.getString("artist"));
					list.add(album);
				}
			}
		}
		return list;
	}

	//アルバムランキング
	public List<Album> getAlbumsOrderedByTraffic() throws Exception {
		List<Album> albumList = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"SELECT album_id, album_name, artist, traffic, album_created_at FROM master_view GROUP BY album_id HAVING COUNT(*) >= 4 ORDER BY traffic desc")) {
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					Album album = new Album();
					album.setId(rs.getInt("album_id"));
					album.setName(rs.getString("album_name"));
					album.setArtist(rs.getString("artist"));
					Timestamp createdAt = rs.getTimestamp("album_created_at");
					album.setCreatedAt(createdAt);
					albumList.add(album);
					System.out.println("daoテスト" + album.getArtist());
				}
			}
		}
		return albumList;
	}

	//シングルランキング
	public List<Album> getSinglesOrderedByTraffic() throws Exception {
		List<Album> singleList = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"SELECT album_id, album_name, artist, traffic, album_created_at FROM master_view GROUP BY album_id HAVING COUNT(*) < 4 ORDER BY traffic desc")) {
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					Album album = new Album();
					album.setId(rs.getInt("album_id"));
					album.setName(rs.getString("album_name"));
					album.setArtist(rs.getString("artist"));
					Timestamp createdAt = rs.getTimestamp("album_created_at");
					album.setCreatedAt(createdAt);
					singleList.add(album);
				}
			}
		}
		return singleList;
	}

	//	カテゴリー別新着順
	public List<Album> getAlbumsSortedByCategoryOrderedByDate(int categoryId) throws Exception {
		List<Album> albumList = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"select * from album_category_view where category_ID = ? order by created_at desc")) {
			st.setInt(1, categoryId);
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					Album album = new Album();
					album.setId(rs.getInt("album_id"));
					album.setCategoryId(rs.getInt("category_id"));
					album.setName(rs.getString("album_name"));
					album.setArtist(rs.getString("artist"));
					Timestamp createdAt = rs.getTimestamp("created_at");
					album.setCreatedAt(createdAt);
					album.setCategoryName(rs.getString("category_name"));
					album.setCategoryImgName(rs.getString("category_img_name"));
					albumList.add(album);
				}
			}
		}
		return albumList;
	}

	//	カテゴリー別ランキング
	public List<Album> getAlbumsSortedByCategoryOrderedByTraffic(int categoryId) throws Exception {
		List<Album> albumList = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"select * from album_category_view where category_ID = ? order by traffic desc")) {
			st.setInt(1, categoryId);
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					Album album = new Album();
					album.setId(rs.getInt("album_id"));
					album.setName(rs.getString("album_name"));
					album.setArtist(rs.getString("artist"));
					Timestamp createdAt = rs.getTimestamp("created_at");
					album.setCreatedAt(createdAt);
					album.setCategoryName(rs.getString("category_name"));
					albumList.add(album);
				}
			}
		}
		return albumList;
	}

	//	カテゴリ取得
	public List<Category> getCategories() throws Exception {
		List<Category> categoryList = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"select * from categories")) {
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					Category category = new Category();
					category.setId(rs.getInt("id"));
					category.setName(rs.getString("category_name"));
					category.setImgName(rs.getString("img_name"));
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
	
	//	検索結果を返す
	public List<Song> getAlbumsSearchedWithKeyword(String keyword) throws Exception {
		List<Song> songList = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"select * from master_view where album_name like ? or artist like ? or song_name like ? or category_name like ?")) {
			String placeHolder = '%' + keyword + '%';
			st.setString(1, placeHolder);
			st.setString(2, placeHolder);
			st.setString(3, placeHolder);
			st.setString(4, placeHolder);
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					Song song = new Song();
					song.setAlbumId(rs.getInt("album_id"));
					song.setName(rs.getString("song_name"));
					song.setAlbumName(rs.getString("album_name"));
					song.setArtist(rs.getString("artist"));
					Timestamp createdAt = rs.getTimestamp("album_created_at");
					song.setCreatedAt(createdAt);
					Timestamp updatedAt = rs.getTimestamp("album_updated_at");
					song.setUpdatedAt(updatedAt);
					songList.add(song);
				}
			}
		}
		return songList;
	}

	//ランキング更新処理
	public void updateAlbumTraffic(int albumIdToSearchSongs) throws Exception {
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"update albums set traffic = traffic + 1 where id = ?")) {
			st.setInt(1, albumIdToSearchSongs);
			st.executeUpdate();
		}
	}

	//	閲覧履歴テーブルの更新
	public void updateUserAlbumStatus(int userId, int albumIdToSearchSongs) throws Exception {
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"insert into album_statuses (user_id, album_id) values (?, ?)")) {
			st.setInt(1, userId);
			st.setInt(2, albumIdToSearchSongs);
			st.executeUpdate();
		}
	}

	//	閲覧履歴取得
	public List<Album> getAlbumsClicked(int userId) throws Exception {
		List<Album> albumList = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"select user_id, album_id, albums.album_name, albums.artist, album_statuses.created_at from album_statuses inner join albums on album_statuses.album_id=albums.id where user_id = ? order by album_statuses.created_at desc")) {
			st.setInt(1, userId);
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					Album album = new Album();
					album.setId(rs.getInt("album_id"));
					album.setName(rs.getString("album_name"));
					album.setArtist(rs.getString("artist"));
					albumList.add(album);
				}
			}
		}
		
		Iterator<Album> iterator = albumList.iterator();
		List<Album> realFinalAlbumList = new ArrayList<>();
		
		if (iterator.hasNext()) {
            Album previous = iterator.next();
            realFinalAlbumList.add(previous);
            
            while (iterator.hasNext()) {
                Album current = iterator.next();
                if (previous.getId() != current.getId()) {
                	realFinalAlbumList.add(current);
                	previous = current;
                }
            }
		}
		return realFinalAlbumList;
	}

	//	ここから曲
	//	アルバムidで取る方法
	public List<Song> getSongswithAlbumId(int albumIdToSearchSongs) throws Exception {
		List<Song> songList = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"select * from master_view where album_id = ?")) {
			st.setInt(1, albumIdToSearchSongs);
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					Song song = new Song();
					song.setId(rs.getInt("song_id"));
					song.setAlbumId(rs.getInt("album_id"));
					song.setCategoryId(rs.getInt("category_id"));
					song.setName(rs.getString("song_name"));
					song.setAlbumName(rs.getString("album_name"));
					song.setAlbumImgName(rs.getString("album_img_name"));
					song.setArtist(rs.getString("artist"));
					song.setPrice(rs.getInt("price"));
					songList.add(song);
				}
			}
		}
		return songList;
	}

	//	曲idで取る方法
	public Song getSongswithSongId(int songIdToSearchSongs) throws Exception {
		Song song = new Song();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"select * from master_view where song_id = ?")) {
			st.setInt(1, songIdToSearchSongs);
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					song.setId(rs.getInt("song_id"));
					song.setAlbumId(rs.getInt("album_id"));
					song.setName(rs.getString("song_name"));
					song.setAlbumName(rs.getString("album_name"));
					song.setArtist(rs.getString("artist"));
					song.setPrice(rs.getInt("price"));
				}
			}
		}
		return song;
	}

	//	ログイン中のユーザーの、カートステータス取得
	public List<Integer> getInCartList(int userId) throws Exception {
		List<Integer> inCartList = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"select song_id from song_statuses where user_id = ? and status = 1")) {
			st.setInt(1, userId);
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					inCartList.add(rs.getInt("song_id"));
				}
			}
		}
		return inCartList;
	}

	//	ログイン中のユーザーの、購入履歴ステータス取得
	public List<Integer> getPurchaseHistoryList(int userId) throws Exception {
		List<Integer> purchaseHistoryList = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"select song_id from song_statuses where user_id = ? and status = 2")) {
			st.setInt(1, userId);
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					purchaseHistoryList.add(rs.getInt("song_id"));
				}
			}
		}
		return purchaseHistoryList;
	}

	//	カート追加処理
	public int getSongIntoCart(int userId, int songId) throws Exception {
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"update song_statuses set status = 1 where user_id = ? and song_id = ?")) {
			st.setInt(1, userId);
			st.setInt(2, songId);
			int line = st.executeUpdate();
			return line;
		}
	}
	
	//	カート削除処理
	public int removeSongFromCart(int userId, int songId) throws Exception {
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"update song_statuses set status = 100 where user_id = ? and song_id = ?")) {
			st.setInt(1, userId);
			st.setInt(2, songId);
			int line = st.executeUpdate();
			return line;
		}
	}

	//	購入処理
	public void purchase(int userId, int songId) throws Exception {
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"update song_statuses set status = 2 where user_id = ? and song_id = ?")) {
			st.setInt(1, userId);
			st.setInt(2, songId);
			st.executeUpdate();
		}
	}

	//	カート、購入履歴取得処理
	public Master getMasterInfo(int songId) throws Exception {
		Master master = new Master();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"select * from master_view where song_id = ?")) {
			st.setInt(1, songId);
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					master.setSongId(rs.getInt("song_id"));
					master.setAlbumId(rs.getInt("album_id"));
					master.setSongName(rs.getString("song_name"));
					master.setAlbumName(rs.getString("album_name"));
					master.setArtist(rs.getString("artist"));
					master.setPrice(rs.getInt("price"));
					Timestamp updatedAt = rs.getTimestamp("album_updated_at");
					master.setUpdatedAt(updatedAt);
				}
			}
		}
		return master;
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
