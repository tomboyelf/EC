package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.beans.User;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.tool.Action;
import jp.co.aforce.tool.Message;

public class ShowPurchaseHistoryAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		User user = new User();
		Message msg = new Message();
		ProductDAO dao = new ProductDAO();
		
//		ログインしてないなら
		user = (User) session.getAttribute("user");
		if (user == null) {
			return "login.jsp";
		}
		
//		購入履歴が空なら
		List<Integer> purchaseHistoryList = dao.getPurchaseHistoryList(user.getId());
		if (purchaseHistoryList == null || purchaseHistoryList.size() == 0) {
			request.setAttribute("purchaseErrorMsg", msg.getPurchaseErrorMsg(6));
			return "purchase-history.jsp";
		}
		return "purchase-history.jsp";
	}
}