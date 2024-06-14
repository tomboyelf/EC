package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.beans.Album;
import jp.co.aforce.beans.User;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.dao.UserDAO;
import jp.co.aforce.tool.Action;

public class LoginAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		UserDAO dao = new UserDAO();
		ProductDAO dao2 = new ProductDAO();
		response.setContentType("text/html; charset=UTF-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		//		db照合
		User user = dao.login(username, password);

		//		入力に誤りがあった場合
		if (user.getLoginErrorMsg() != null) {
			String loginErrorMsg = user.getLoginErrorMsg();

			request.setAttribute("loginErrorMsg", loginErrorMsg);
			request.setAttribute("username", username);
			return "login.jsp";
		}
		session.setAttribute("user", user);

		//		閲覧履歴
		List<Album> albumList_hisotry = dao2.getAlbumsClicked(user.getId());
		session.setAttribute("albumList_hisotry", albumList_hisotry);

		//		カート状況
		List<Integer> inCartList = dao2.getInCartList(user.getId());
		session.setAttribute("inCartList", inCartList);

		//		購入履歴
		List<Integer> purchaseHistoryList = dao2.getPurchaseHistoryList(user.getId());
		session.setAttribute("purchaseHistoryList", purchaseHistoryList);

		return "index.jsp";
	}

}
