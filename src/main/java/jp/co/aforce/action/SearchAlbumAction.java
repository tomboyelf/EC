package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.aforce.beans.Album;
import jp.co.aforce.beans.Category;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.tool.Action;

public class SearchAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductDAO dao = new ProductDAO();

		//		新着一覧取得
		if (request.getParameter("searchId") != null) {
			if (request.getParameter("searchId").equals("orderedByDate")) {
				List<Album> albumListOrderedByDate = dao.showAlbumOrderedByDate();
				request.setAttribute("albumListOrderedByDate", albumListOrderedByDate);
			}

			//		ランキング一覧取得
			if (request.getParameter("searchId").equals("orderedByTraffic")) {
				List<Album> albumListOrderedByTraffic = dao.showAlbumOrderedByTraffic();
				request.setAttribute("albumListOrderedByTraffic", albumListOrderedByTraffic);
			}

			//		これだけアルバムではなくカテゴリーの取得
			//		カテゴリー名一覧取得
			if (request.getParameter("searchId").equals("categoryPage")) {
				List<Category> categoryList = dao.showCategory();
				request.setAttribute("categoryList", categoryList);
			}
		}
		
		//		検索結果一覧取得
		if (request.getParameter("keyword") != null) {
			String keyword = request.getParameter("keyword");
			request.setAttribute("keyword", keyword);
			List<Album> albumListSearchedByKeyword = dao.showAlbumSearchedByKeyword(keyword);
			request.setAttribute("albumListSearchedByKeyword", albumListSearchedByKeyword);
		}

		//		カテゴリー別一覧取得
		if (request.getParameter("categoryId") != null) {
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			List<Album> albumListSortedByCategory = dao.showAlbumSortedByCategory(categoryId);
			request.setAttribute("albumListSortedByCategory", albumListSortedByCategory);
		}

		return "album.jsp";
	}

}