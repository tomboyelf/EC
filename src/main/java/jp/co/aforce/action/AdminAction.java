package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.beans.Album;
import jp.co.aforce.beans.Category;
import jp.co.aforce.beans.Song;
import jp.co.aforce.beans.User;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.tool.Action;

public class AdminAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ProductDAO productDao = new ProductDAO();
		User user = new User();
//		Message msg = new Message();
		user = (User) session.getAttribute("user");
		
		//		ログインチェック、鰹節
		if (user == null || user.getId() != 1) {
			//		ログインを促す
			return "login.jsp";
		}
		
		if (request.getParameter("adminId") != null) {
			
//		カテゴリー設定へ
			if(request.getParameter("adminId").equals("category")) {
				List<Category> categoryList = productDao.getCategories();
				request.setAttribute("categoryList", categoryList);
				request.setAttribute("admin", "admin");
				return "admin-product.jsp";
			}
//		アルバム設定へ
			if(request.getParameter("adminId").equals("album")) {
				List<Album> albumList = productDao.getAlbumAndSingleOrderedByDate();
				request.setAttribute("albumList", albumList);
				List<Category> categoryList = productDao.getCategories();
				request.setAttribute("categoryOption", categoryList);
				request.setAttribute("admin", "admin");
				return "admin-product.jsp";
			}
//		曲設定へ
			if(request.getParameter("adminId").equals("song")) {
				List<Song> songList = productDao.getSongs();
				request.setAttribute("songList", songList);
				List<Album> albumList = productDao.getAlbumAndSingleOrderedByDate();
				request.setAttribute("albumOption", albumList);
				request.setAttribute("admin", "admin");
				return "admin-product.jsp";
			}
//		顧客情報へ
			if(request.getParameter("adminId").equals("user")) {
				List<Song> songList = productDao.getSongs();
				request.setAttribute("songList", songList);
				List<Album> albumList = productDao.getAlbumAndSingleOrderedByDate();
				request.setAttribute("albumOption", albumList);
				return "admin-product.jsp";
			}
			
			
			
		}
		
		
		
//		カテゴリー別
		if (request.getParameter("categoryId") != null) {
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			Category category = productDao.getSpecificCategory(categoryId);
			request.setAttribute("category", category);
			List<Album> albumListSortedByCategory = productDao.getAlbumsSortedByCategoryOrderedByDate(categoryId);
			request.setAttribute("albumListSortedByCategory", albumListSortedByCategory);
			return "admin-product.jsp";
		}
//		アルバム別
		if (request.getParameter("albumId") != null) {
			int albumId = Integer.parseInt(request.getParameter("albumId"));
			Album album = productDao.getSpecificAlbum(albumId);
			request.setAttribute("album", album);
			List<Song> songListWithAlbumId = productDao.getSongswithAlbumId(albumId);
			request.setAttribute("songListWithAlbumId", songListWithAlbumId);
			List<Category> categoryList = productDao.getCategories();
			request.setAttribute("categoryOption", categoryList);
			return "admin-product.jsp";
		}
//		曲別
		if (request.getParameter("songId") != null) {
			int songId = Integer.parseInt(request.getParameter("songId"));
			Song song = productDao.getSpecificSong(songId);
			request.setAttribute("song", song);
			List<Album> albumList = productDao.getAlbumAndSingleOrderedByDate();
			request.setAttribute("albumOption", albumList);
			return "admin-product.jsp";
		}
		
		if (request.getParameter("songChangeId") != null) {
			if (request.getParameter("songChangeId").equals("nameChange")) {
				int songId = Integer.parseInt(request.getParameter("oldSongId"));
				Song oldSong = productDao.getSpecificSong(songId);
				
				String newName = request.getParameter("name");
				
				Song newSong = new Song(songId, oldSong.getAlbumId(), oldSong.getAudioName(), newName, oldSong.getAlbumName(), oldSong.getPrice());
				request.setAttribute("oldSong", oldSong);
				request.setAttribute("newSong", newSong);
				return "confirm.jsp";
			}
			if (request.getParameter("songChangeId").equals("priceChange")) {
				int songId = Integer.parseInt(request.getParameter("oldSongId"));
				Song oldSong = productDao.getSpecificSong(songId);
				
				int newPrice = Integer.parseInt(request.getParameter("price"));
				
				Song newSong = new Song(songId, oldSong.getAlbumId(), oldSong.getAudioName(), oldSong.getName(), oldSong.getAlbumName(), newPrice);
				request.setAttribute("oldSong", oldSong);
				request.setAttribute("newSong", newSong);
				return "confirm.jsp";
			}
			if (request.getParameter("songChangeId").equals("albumChange")) {
				int songId = Integer.parseInt(request.getParameter("oldSongId"));
				Song oldSong = productDao.getSpecificSong(songId);
				
				int newAlbumId = Integer.parseInt(request.getParameter("albumOption"));
				Album album = productDao.getSpecificAlbum(newAlbumId);
				
				Song newSong = new Song(songId, newAlbumId, oldSong.getAudioName(), oldSong.getName(), album.getName(), oldSong.getPrice());
				request.setAttribute("oldSong", oldSong);
				request.setAttribute("newSong", newSong);
				return "confirm.jsp";
			}
		}
		
		System.out.println("seikou");
		return "0";
		

	}
}