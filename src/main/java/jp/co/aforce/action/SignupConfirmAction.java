package jp.co.aforce.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.beans.User;
import jp.co.aforce.dao.UserDAO;
import jp.co.aforce.tool.Action;

public class SignupConfirmAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		response.setContentType("text/html; charset=UTF-8");

		try {
			UserDAO dao = new UserDAO();

			//dbへ登録
			User notTrueFinalRealuser = new User();
			notTrueFinalRealuser = (User) session.getAttribute("notTrueFinalRealuser");

			System.out.println("name:" + notTrueFinalRealuser.getUsername());
			dao.insert(notTrueFinalRealuser);
			session.removeAttribute("notTrueFinalRealuser");

			//ログイン処理
			User user = new User();
			user = dao.login(notTrueFinalRealuser.getUsername(), notTrueFinalRealuser.getPassword());
			session.setAttribute("user", user);

			return "index.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "signup.jsp";
		}

	}

}
