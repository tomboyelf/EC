package jp.co.aforce.action;

import java.text.SimpleDateFormat;

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
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			UserDAO dao = new UserDAO();

			//仮登録情報の取得

			String username = request.getParameter("confirmUsername");
			String password = request.getParameter("confirmPassword");
			String lastname = request.getParameter("confirmLastname");
			String firstname = request.getParameter("confirmFirstname");
			String sex = request.getParameter("confirmSex");
			String birthdateString = request.getParameter("confirmBirthdate");
			String mailaddress = request.getParameter("confirmMailaddress");

			java.util.Date birthdateUtil = dateFormat.parse(birthdateString);
			java.sql.Date birthdate = new java.sql.Date(birthdateUtil.getTime());

			User confirmedUser = new User(username, password, lastname, firstname, sex, birthdate, mailaddress);

			//登録
			if (dao.insert(confirmedUser) > 0) {
				//ログイン処理
				User user = new User();
				user = dao.login(confirmedUser.getUsername(), confirmedUser.getPassword());
				session.setAttribute("user", user);

				request.setAttribute("completeMsg", msg.getCompleteMsg(0));
				return "message.jsp";
			} else {
				request.setAttribute("signupErrorMsg", msg.getSignupErrorMsg(7));
				return "signup.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "signup.jsp";
		}

	}

}
