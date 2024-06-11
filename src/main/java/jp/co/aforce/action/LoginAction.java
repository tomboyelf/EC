package jp.co.aforce.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.beans.User;
import jp.co.aforce.dao.UserDAO;
import jp.co.aforce.tool.Action;

public class LoginAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		UserDAO dao = new UserDAO();
		response.setContentType("text/html; charset=UTF-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		
		User user = dao.login(username, password);
		if (user.getLoginErrorMsg() != null) {
			String loginErrorMsg = user.getLoginErrorMsg();
			
			request.setAttribute("loginErrorMsg", loginErrorMsg);
			request.setAttribute("username", username);
			return "login.jsp";
		}
		session.setAttribute("user", user);
		return "index.jsp";
	}

}
