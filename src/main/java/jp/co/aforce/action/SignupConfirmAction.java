package jp.co.aforce.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.beans.User;
import jp.co.aforce.dao.UserDAO;
import jp.co.aforce.tool.Action;
import jp.co.aforce.tool.Message;

public class SignupConfirmAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		response.setContentType("text/html; charset=UTF-8");
		Message msg = new Message();

		try {
			UserDAO dao = new UserDAO();

			//仮登録情報の取得
			User ultraFinalUser = new User();
			ultraFinalUser = (User) session.getAttribute("notTrueFinalRealuser");

			//登録
			dao.insert(ultraFinalUser);
			session.removeAttribute("notTrueFinalRealuser");

			//ログイン処理
			User user = new User();
			user = dao.login(ultraFinalUser.getUsername(), ultraFinalUser.getPassword());
			session.setAttribute("user", user);
			
			request.setAttribute("completeMsg", msg.getCompleteMsg(0));
			return "message.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "signup.jsp";
		}

	}

}
