package jp.co.aforce.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.tool.Action;

public class LogoutAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		session.removeAttribute("user");
		session.removeAttribute("albumList_hisotry");
		session.removeAttribute("albumList_hisotry");
		session.removeAttribute("inCartList");
		session.removeAttribute("purchaseHistoryList");
		return "index.jsp";
	}

}