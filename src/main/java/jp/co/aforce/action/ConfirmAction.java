package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.beans.User;
import jp.co.aforce.dao.AdminDAO;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.tool.Action;
import jp.co.aforce.tool.Message;

public class ConfirmAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ProductDAO productDao = new ProductDAO();
		AdminDAO adminDao = new AdminDAO();
		Message msg = new Message();

		User user = (User) session.getAttribute("user");

		//		最終購入処理
		if (request.getParameterValues("selectedSongs") != null
				&& request.getParameterValues("selectedSongs").length != 0) {
			String[] selectedSongIds = request.getParameterValues("selectedSongs");
			//		ユーザーごとの購入履歴更新
			for (String i : selectedSongIds) {
				int songId = Integer.parseInt(i);
				productDao.purchase(user.getId(), songId);
			}

			//		新しい購入履歴取得
			List<Integer> purchaseHistoryList = productDao.getPurchaseHistoryList(user.getId());
			session.setAttribute("purchaseHistoryList", purchaseHistoryList);
			request.setAttribute("completeMsg", msg.getCompleteMsg(3));
			return "message.jsp";
		}

		//		カテゴリ変更
		//		・3つの値がとれていないとき
		//		・sql操作がうまくいかなかったとき
//		カテゴリ情報変更
		if (request.getParameter("newCategoryId") != null && request.getParameter("newCategoryImgName") != null
				&& request.getParameter("newCategoryName") != null) {
			int categoryId = Integer.parseInt(request.getParameter("newCategoryId"));
			String categoryImgName = request.getParameter("newCategoryImgName");
			String categoryName = request.getParameter("newCategoryName");
			
			int line = adminDao.changeCategoryImgAndCategoryName(categoryImgName, categoryName, categoryId);
			if (line > 0) {
				request.setAttribute("adminCompleteMsg", msg.getAdminCompleteMsg(0));
				return "message.jsp";
			}
		}
		
//		カテゴリ新規作成
		if (request.getParameter("newCategoryId") == null && request.getParameter("newCategoryImgName") != null && request.getParameter("newCategoryName") != null) {
			String categoryImgName = request.getParameter("newCategoryImgName");
			String categoryName = request.getParameter("newCategoryName");
			int line = adminDao.createNewCategory(categoryImgName, categoryName);
			if (line > 0) {
				request.setAttribute("adminCompleteMsg", msg.getAdminCompleteMsg(3));
				return "message.jsp";
			}
		}
		
//		アルバム情報変更
		if (request.getParameter("newAlbumId") != null && request.getParameter("newAlbumImgName") != null && request.getParameter("newAlbumName") != null && request.getParameter("newAlbumArtist") != null && request.getParameter("newAlbumCategory") != null) {
			String albumImgName = request.getParameter("newAlbumImgName");
			String albumName = request.getParameter("newAlbumName");
			String artist = request.getParameter("newAlbumArtist");
			int categoryId = Integer.parseInt(request.getParameter("newAlbumCategory"));
			int albumId = Integer.parseInt(request.getParameter("newAlbumId"));
			System.out.println(albumImgName + albumName + artist + categoryId + albumId);
			
			int line = adminDao.changeAlbum(albumImgName, albumName, artist,  categoryId, albumId);
			if (line > 0) {
				request.setAttribute("adminCompleteMsg", msg.getAdminCompleteMsg(0));
				return "message.jsp";
			}
		}
		
//		アルバム新規作成
		if (request.getParameter("newAlbumId") == null && request.getParameter("newAlbumImgName") != null && request.getParameter("newAlbumName") != null && request.getParameter("newAlbumArtist") != null && request.getParameter("newAlbumCategory") != null) {
			String albumImgName = request.getParameter("newAlbumImgName");
			String albumName = request.getParameter("newAlbumName");
			String artist = request.getParameter("newAlbumArtist");
			int categoryId = Integer.parseInt(request.getParameter("newAlbumCategory"));
			int line = adminDao.createNewAlbum(albumImgName, albumName, artist, categoryId);
			if (line > 0) {
				request.setAttribute("adminCompleteMsg", msg.getAdminCompleteMsg(3));
				return "message.jsp";
			}
		}
		return "";
	}
}