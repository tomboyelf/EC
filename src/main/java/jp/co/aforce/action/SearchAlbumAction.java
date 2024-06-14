package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.aforce.beans.Album;
import jp.co.aforce.beans.Category;
import jp.co.aforce.beans.Song;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.tool.Action;
import jp.co.aforce.tool.Sort;

public class SearchAlbumAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductDAO dao = new ProductDAO();

		if (request.getParameter("searchId") != null) {
			//		新着一覧取得
			if (request.getParameter("searchId").equals("orderedByDate")) {
				//				新着全部
				List<Album> albumAndSingleList_date = dao.getAlbumAndSingleOrderedByDate();
				request.setAttribute("albumAndSingleList_date", albumAndSingleList_date);
				//				新着のアルバムリスト、今週と先週
				List<Album> albumList_date = dao.getAlbumsOrderedByDate();
				List<Album> albumListWeekly = Sort.getAlbumListWeekly(albumList_date);
				List<Album> albumListLastWeekly = Sort.getAlbumListLastWeekly(albumList_date);
				request.setAttribute("albumList_date", albumList_date);
				request.setAttribute("albumListWeekly", albumListWeekly);
				request.setAttribute("albumListLastWeekly", albumListLastWeekly);

				//				新着のシングルリスト、今週と先週
				List<Album> singleList_date = dao.getSinglesOrderedByDate();
				List<Album> singleListWeekly = Sort.getAlbumListWeekly(singleList_date);
				List<Album> singleListLastWeekly = Sort.getAlbumListLastWeekly(singleList_date);
				request.setAttribute("singleList_date", singleList_date);
				request.setAttribute("singleListWeekly", singleListWeekly);
				request.setAttribute("singleListLastWeekly", singleListLastWeekly);
			}

			//		ランキング一覧取得
			if (request.getParameter("searchId").equals("orderedByTraffic")) {
				//				ランキング全部
				List<Album> albumAndSingleList_traffic = dao.getAlbumAndSingleOrderedByTraffic();
				request.setAttribute("albumAndSingleList_traffic", albumAndSingleList_traffic);
				//				ランキングのアルバムリスト、今日と今週と今月
				List<Album> albumList_traffic = dao.getAlbumsOrderedByTraffic();
				List<Album> albumListDaily = Sort.getAlbumListDaily(albumList_traffic);
				List<Album> albumListWeekly = Sort.getAlbumListWeekly(albumList_traffic);
				List<Album> albumListMonthly = Sort.getAlbumListMonthly(albumList_traffic);
				request.setAttribute("albumList_traffic", albumList_traffic);
				request.setAttribute("albumTrafficListDaily", albumListDaily);
				request.setAttribute("albumTrafficListWeekly", albumListWeekly);
				request.setAttribute("albumTrafficListMonthly", albumListMonthly);
				//				ランキングのアルバムリスト、今日と今週と今月
				List<Album> singleList_traffic = dao.getSinglesOrderedByTraffic();
				List<Album> singleListDaily = Sort.getAlbumListDaily(singleList_traffic);
				List<Album> singleListWeekly = Sort.getAlbumListWeekly(singleList_traffic);
				List<Album> singleListMonthly = Sort.getAlbumListMonthly(singleList_traffic);
				request.setAttribute("singleList_traffic", singleList_traffic);
				request.setAttribute("singleTrafficListDaily", singleListDaily);
				request.setAttribute("singleTrafficListWeekly", singleListWeekly);
				request.setAttribute("singleTrafficListsMonthly", singleListMonthly);
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
			List<Song> songList_keyword = dao.getAlbumsSearchedWithKeyword(keyword);
			request.setAttribute("songList_keyword", songList_keyword);
		}

		//		カテゴリー別一覧取得
		if (request.getParameter("categoryId") != null) {
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			List<Album> albumList_categoryOrderedByDate = dao.getAlbumsSortedByCategoryOrderedByDate(categoryId);
			request.setAttribute("albumList_categoryOrderedByDate", albumList_categoryOrderedByDate);
			List<Album> albumList_categoryOrderedByTraffic = dao.getAlbumsSortedByCategoryOrderedByTraffic(categoryId);
			request.setAttribute("albumList_categoryOrderedByTraffic", albumList_categoryOrderedByTraffic);
		}

		return "album.jsp";
	}

}