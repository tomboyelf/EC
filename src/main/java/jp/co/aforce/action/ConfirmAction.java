package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.beans.User;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.tool.Action;
import jp.co.aforce.tool.Message;

public class ConfirmAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ProductDAO dao = new ProductDAO();
		Message msg = new Message();
		
		User user = (User) session.getAttribute("user");
		
//		最終購入処理
		if(request.getParameterValues("selectedSongs") != null && request.getParameterValues("selectedSongs").length != 0) {
			String[] selectedSongIds = request.getParameterValues("selectedSongs");
//		ユーザーごとの購入履歴更新
			for(String i: selectedSongIds) {
				int songId = Integer.parseInt(i);
				dao.purchase(user.getId(), songId);
			}
			
//		新しい購入履歴取得
			List<Integer> purchaseHistoryList = dao.getPurchaseHistoryList(user.getId());
			session.setAttribute("purchaseHistoryList", purchaseHistoryList);
			request.setAttribute("completeMsg", msg.getCompleteMsg(3)); 
			return "message.jsp";
		}
		
		
//		カテゴリ変更
//		・3つの値がとれていないとき
//		・sql操作がうまくいかなかったとき
		if(request.getParameter("newCategoryId") != null && request.getParameter("newCategoryImgName") != null && request.getParameter("newCategoryName") != null) {
			int categoryId = Integer.parseInt(request.getParameter("newCategoryId"));  
			String categoryImgName = request.getParameter("newCategoryImgName");
			String categoryName = request.getParameter("newCategoryName");
			
			int line = dao.changeCategoryImgAndCategoryName(categoryImgName, categoryName, categoryId);
			if(line > 0) {
				request.setAttribute("adminCompleteMsg", msg.getAdminCompleteMsg(0));
				return "message.jsp";
			}
		}
		return "";
	}
}