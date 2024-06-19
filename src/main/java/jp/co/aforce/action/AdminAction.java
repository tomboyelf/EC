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
		ProductDAO dao = new ProductDAO();
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
				List<Category> categoryList = dao.getCategories();
				request.setAttribute("categoryList", categoryList);
				return "admin-product.jsp";
			}
//		アルバム設定へ
			if(request.getParameter("adminId").equals("album")) {
				List<Album> albumList = dao.getAlbumAndSingleOrderedByDate();
				request.setAttribute("albumList", albumList);
				List<Category> categoryList = dao.getCategories();
				request.setAttribute("categoryOption", categoryList);
				return "admin-product.jsp";
			}
		}
//		カテゴリー別
		if (request.getParameter("categoryId") != null) {
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			List<Album> albumListSortedByCategory = dao.getAlbumsSortedByCategoryOrderedByDate(categoryId);
			request.setAttribute("albumListSortedByCategory", albumListSortedByCategory);
			return "admin-product.jsp";
		}
//		アルバム別
		if (request.getParameter("albumId") != null) {
			int albumId = Integer.parseInt(request.getParameter("albumId"));
			Album album = dao.getSpecificAlbum(albumId);
			System.out.println(album.getAlbumImgName());
			request.setAttribute("album", album);
			List<Song> songListWithAlbumId = dao.getSongswithAlbumId(albumId);
			request.setAttribute("songListWithAlbumId", songListWithAlbumId);
			List<Category> categoryList = dao.getCategories();
			request.setAttribute("categoryOption", categoryList);
			return "admin-product.jsp";
		}
		
		
		System.out.println("seikou");
		return "0";
		

	}
}