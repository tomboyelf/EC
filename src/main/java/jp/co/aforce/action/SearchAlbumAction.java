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
import jp.co.aforce.tool.Sort;

public class SearchAlbumAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		session.removeAttribute("songsInAlbum");
		ProductDAO productDao = new ProductDAO();
		User user = new User();
		user = (User) session.getAttribute("user");
		
		if (user != null) {
			//		閲覧履歴
			List<Album> albumListHisotry = productDao.getAlbumsClicked(user.getId());
			session.setAttribute("albumListHisotry", albumListHisotry);
		}

		if (request.getParameter("searchId") != null) {
			//		新着一覧取得
			if (request.getParameter("searchId").equals("orderedByDate")) {
				//				新着全部
				List<Album> albumAndSingleListDate = productDao.getAlbumAndSingleOrderedByDate();
				request.setAttribute("albumAndSingleListDate", albumAndSingleListDate);
				//				新着のアルバムリスト、今週と先週
				List<Album> albumListDate = productDao.getAlbumsOrderedByDate();
				List<Album> albumListWeekly = Sort.getAlbumListWeekly(albumListDate);
				List<Album> albumListLastWeekly = Sort.getAlbumListLastWeekly(albumListDate);
				request.setAttribute("albumListDate", albumListDate);
				request.setAttribute("albumListWeekly", albumListWeekly);
				request.setAttribute("albumListLastWeekly", albumListLastWeekly);

				//				新着のシングルリスト、今週と先週
				List<Album> singleListDate = productDao.getSinglesOrderedByDate();
				List<Album> singleListWeekly = Sort.getAlbumListWeekly(singleListDate);
				List<Album> singleListLastWeekly = Sort.getAlbumListLastWeekly(singleListDate);
				request.setAttribute("singleListDate", singleListDate);
				request.setAttribute("singleListWeekly", singleListWeekly);
				request.setAttribute("singleListLastWeekly", singleListLastWeekly);
			}

			//		ランキング一覧取得
			if (request.getParameter("searchId").equals("orderedByTraffic")) {
				//				ランキング全部
				List<Album> albumAndSingleListTraffic = productDao.getAlbumAndSingleOrderedByTraffic();
				request.setAttribute("albumAndSingleListTraffic", albumAndSingleListTraffic);
				//				ランキングのアルバムリスト
				List<Album> albumListTraffic = productDao.getAlbumsOrderedByTraffic();
				request.setAttribute("albumListTraffic", albumListTraffic);
				//				ランキングのアルバムリスト
				List<Album> singleListTraffic = productDao.getSinglesOrderedByTraffic();
				request.setAttribute("singleListTraffic", singleListTraffic);
			}

			//		これだけアルバムではなくカテゴリーの取得
			//		カテゴリー名一覧取得
			if (request.getParameter("searchId").equals("categoryPage")) {
				List<Category> categoryList = productDao.getCategories();
				request.setAttribute("categoryList", categoryList);
			}
		}

		//		検索結果一覧取得
		if (request.getParameter("keyword") != null) {
			String keyword = request.getParameter("keyword");
			//			リクエスト付与した理由
			//			→結果画面にテキスト表示させるため
			request.setAttribute("keyword", keyword);
			List<Song> songListKeyword = productDao.getAlbumsSearchedWithKeyword(keyword);
			request.setAttribute("songListKeyword", songListKeyword);
		}

		//		カテゴリー別一覧取得
		if (request.getParameter("categoryId") != null) {
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			List<Album> albumListInCategoryOrderedByDate = productDao
					.getAlbumsSortedByCategoryOrderedByDate(categoryId);
			request.setAttribute("albumListInCategoryOrderedByDate", albumListInCategoryOrderedByDate);
			List<Album> albumListInCategoryOrderedByTraffic = productDao
					.getAlbumsSortedByCategoryOrderedByTraffic(categoryId);
			request.setAttribute("albumListInCategoryOrderedByTraffic", albumListInCategoryOrderedByTraffic);
		}

		return "album.jsp";
	}

}