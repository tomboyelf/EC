package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.beans.Album;
import jp.co.aforce.beans.Category;
import jp.co.aforce.beans.Song;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.tool.Action;
import jp.co.aforce.tool.Sort;

public class SearchAlbumAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		session.removeAttribute("songsInAlbum");
		
		ProductDAO dao = new ProductDAO();

		if (request.getParameter("searchId") != null) {
			//		新着一覧取得
			if (request.getParameter("searchId").equals("orderedByDate")) {
				//				新着全部
				List<Album> albumAndSingleListDate = dao.getAlbumAndSingleOrderedByDate();
				request.setAttribute("albumAndSingleListDate", albumAndSingleListDate);
				//				新着のアルバムリスト、今週と先週
				List<Album> albumListDate = dao.getAlbumsOrderedByDate();
				List<Album> albumListWeekly = Sort.getAlbumListWeekly(albumListDate);
				List<Album> albumListLastWeekly = Sort.getAlbumListLastWeekly(albumListDate);
				request.setAttribute("albumListDate", albumListDate);
				request.setAttribute("albumListWeekly", albumListWeekly);
				request.setAttribute("albumListLastWeekly", albumListLastWeekly);

				//				新着のシングルリスト、今週と先週
				List<Album> singleListDate = dao.getSinglesOrderedByDate();
				List<Album> singleListWeekly = Sort.getAlbumListWeekly(singleListDate);
				List<Album> singleListLastWeekly = Sort.getAlbumListLastWeekly(singleListDate);
				request.setAttribute("singleListDate", singleListDate);
				request.setAttribute("singleListWeekly", singleListWeekly);
				request.setAttribute("singleListLastWeekly", singleListLastWeekly);
			}

			//		ランキング一覧取得
			if (request.getParameter("searchId").equals("orderedByTraffic")) {
				//				ランキング全部
				List<Album> albumAndSingleListTraffic = dao.getAlbumAndSingleOrderedByTraffic();
				request.setAttribute("albumAndSingleListTraffic", albumAndSingleListTraffic);
				//				ランキングのアルバムリスト、今日と今週と今月
				List<Album> albumListTraffic = dao.getAlbumsOrderedByTraffic();
				List<Album> albumListDaily = Sort.getAlbumListDaily(albumListTraffic);
				List<Album> albumListWeekly = Sort.getAlbumListWeekly(albumListTraffic);
				List<Album> albumListMonthly = Sort.getAlbumListMonthly(albumListTraffic);
				request.setAttribute("albumListTraffic", albumListTraffic);
				request.setAttribute("albumTrafficListDaily", albumListDaily);
				request.setAttribute("albumTrafficListWeekly", albumListWeekly);
				request.setAttribute("albumTrafficListMonthly", albumListMonthly);
				//				ランキングのアルバムリスト、今日と今週と今月
				List<Album> singleListTraffic = dao.getSinglesOrderedByTraffic();
				List<Album> singleListDaily = Sort.getAlbumListDaily(singleListTraffic);
				List<Album> singleListWeekly = Sort.getAlbumListWeekly(singleListTraffic);
				List<Album> singleListMonthly = Sort.getAlbumListMonthly(singleListTraffic);
				request.setAttribute("singleListTraffic", singleListTraffic);
				request.setAttribute("singleTrafficListDaily", singleListDaily);
				request.setAttribute("singleTrafficListWeekly", singleListWeekly);
				request.setAttribute("singleTrafficListMonthly", singleListMonthly);
			}

			//		これだけアルバムではなくカテゴリーの取得
			//		カテゴリー名一覧取得
			if (request.getParameter("searchId").equals("categoryPage")) {
				List<Category> categoryList = dao.getCategories();
				request.setAttribute("categoryList", categoryList);
			}
		}

		//		検索結果一覧取得
		if (request.getParameter("keyword") != null) {
			String keyword = request.getParameter("keyword");
			//			リクエスト付与した理由
			//			→結果画面にテキスト表示させるため
			request.setAttribute("keyword", keyword);
			List<Song> songListKeyword = dao.getAlbumsSearchedWithKeyword(keyword);
			request.setAttribute("songListKeyword", songListKeyword);
		}

		//		カテゴリー別一覧取得
		if (request.getParameter("categoryId") != null) {
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			List<Album> albumListInCategoryOrderedByDate = dao.getAlbumsSortedByCategoryOrderedByDate(categoryId);
			request.setAttribute("albumListInCategoryOrderedByDate", albumListInCategoryOrderedByDate);
			List<Album> albumListInCategoryOrderedByTraffic = dao.getAlbumsSortedByCategoryOrderedByTraffic(categoryId);
			request.setAttribute("albumListInCategoryOrderedByTraffic", albumListInCategoryOrderedByTraffic);
		}

		return "album.jsp";
	}

}