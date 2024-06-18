package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.beans.User;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.tool.Action;
import jp.co.aforce.tool.Message;

public class ShowCartAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ProductDAO dao = new ProductDAO();
		Message msg = new Message();

		//		ログインしてないなら
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "login.jsp";
		}

		//		カートから削除
		if (request.getParameter("songId") != null) {

			int line = dao.removeSongFromCart(user.getId(), Integer.parseInt(request.getParameter("songId")));
			if (line > 0) {
				request.setAttribute("completeMsg", msg.getCompleteMsg(5));
				List<Integer> inCartList = dao.getInCartList(user.getId());
				session.setAttribute("inCartList", inCartList);
				return "cart.jsp";
			}
		}

		//		カートが空なら
		List<Integer> inCartList = dao.getInCartList(user.getId());
		if (inCartList == null || inCartList.size() == 0) {
			request.setAttribute("purchaseErrorMsg", msg.getPurchaseErrorMsg(5));
			return "cart.jsp";
		}
		return "cart.jsp";
	}
}