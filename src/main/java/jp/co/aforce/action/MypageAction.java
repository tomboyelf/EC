package jp.co.aforce.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.tool.Action;

public class MypageAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		//		鰹節
		if (session.getAttribute("user") == null) {
			return "login.jsp";
		}

		if (request.getParameter("optionId") != null) {
			String optionId = request.getParameter("optionId");
			if (optionId.equals("profile")) {
				request.setAttribute("optionId", optionId);
				return "option.jsp";
			}
			if (optionId.equals("password")) {
				request.setAttribute("optionId", optionId);
				return "option.jsp";
			}
			if (optionId.equals("mailaddress")) {
				request.setAttribute("optionId", optionId);
				return "option.jsp";
			}
			if (optionId.equals("quit")) {
				request.setAttribute("optionId", optionId);
				return "option.jsp";
			}
			if (request.getParameter("optionId").equals("purchasehistory")) {
				return "purchase-history.jsp";
			}
		}

		return "mypage.jsp";
	}
}