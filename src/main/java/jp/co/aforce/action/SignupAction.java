package jp.co.aforce.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.beans.User;
import jp.co.aforce.dao.UserDAO;
import jp.co.aforce.tool.Action;
import jp.co.aforce.tool.Message;
import jp.co.aforce.tool.Valid;

public class SignupAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		response.setContentType("text/html; charset=UTF-8");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Message msg = new Message();

		//		入力フォームの値を取得
		String username = request.getParameter("username");
		String newUsername = request.getParameter("newUsername");
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		String lastname = request.getParameter("lastname");
		String firstname = request.getParameter("firstname");
		String sex = request.getParameter("sex");
		String mailaddress = request.getParameter("mailaddress");
		String newMailaddress = request.getParameter("newMailaddress");
		UserDAO dao = new UserDAO();
		List<Integer> duplicationErrorMessageList = new ArrayList<>();

		//		マイページのアカウント設定
		//		パスワード編
		if (request.getParameter("optionId") != null && request.getParameter("optionId").equals("password")) {
			User user = (User) session.getAttribute("user");

			//			鰹節
			if (user == null) {
				return "login.jsp";
			}

			//			正規表現と照合
			List<Integer> errorMessageList = new ArrayList<>();
			errorMessageList = Valid.validCheckForPassword(newPassword);

			//			問題があればメッセージを返す
			if (errorMessageList.size() != 0) {
				for (int i : errorMessageList) {
					request.setAttribute("signupErrorMsg00" + i, msg.getSignupErrorMsg(i));
				}
				if (user.getPassword() != password) {
					request.setAttribute("loginErrorMsg001", msg.getLoginErrorMsg(1));
				}
				return "option.jsp";
			}

			//			問題なければ登録し再ログイン
			if (dao.insertPassword(newPassword, user.getId()) > 0) {
				user = dao.login(user.getUsername(), newPassword);
				session.setAttribute("user", user);
				request.setAttribute("completeMsg", msg.getCompleteMsg(4));
				return "message.jsp";
			} else {
				request.setAttribute("signupErrorMsg", msg.getSignupErrorMsg(7));
				return "option.jsp";
			}
		}

		//		メールアドレス編
		if (request.getParameter("optionId") != null && request.getParameter("optionId").equals("mailaddress")) {
			User user = (User) session.getAttribute("user");

			//			鰹節
			if (user == null) {
				return "login.jsp";
			}

			if (!user.getMailaddress().equals(mailaddress)) {
				request.setAttribute("signupErrorMsg008", msg.getSignupErrorMsg(8));
				return "option.jsp";
			}

			//			正規表現と照合
			//			006
			List<Integer> errorMessageList = new ArrayList<>();
			errorMessageList = Valid.validCheckForMailaddress(newMailaddress);

			//			dbの重複チェック
			//			001
			try {
				duplicationErrorMessageList = dao.duplicationCheckForMailaddress(newMailaddress);
			} catch (Exception e) {
				e.printStackTrace();
			}
			errorMessageList.addAll(duplicationErrorMessageList);

			//			問題があればメッセージを返す
			if (errorMessageList.size() != 0) {
				for (int i : errorMessageList) {
					request.setAttribute("signupErrorMsg00" + i, msg.getSignupErrorMsg(i));
				}
				return "option.jsp";
			}

			//			問題なければ登録し再ログイン
			if (dao.insertMailaddress(newMailaddress, user.getId()) > 0) {
				user = dao.login(user.getUsername(), user.getPassword());
				session.setAttribute("user", user);
				request.setAttribute("completeMsg", msg.getCompleteMsg(4));
				return "message.jsp";
			} else {
				request.setAttribute("signupErrorMsg007", msg.getSignupErrorMsg(7));
				return "option.jsp";
			}
		}

		//		退会編
		if (request.getParameter("optionId") != null && request.getParameter("optionId").equals("quit")) {
			User user = (User) session.getAttribute("user");
			//			鰹節
			if (user == null) {
				return "login.jsp";
			}

			int line = dao.quit(user.getId());
			if (line >= 1) {
				request.setAttribute("completeMsg", msg.getCompleteMsg(6));
				return "message.jsp";
			} else {
				request.setAttribute("signupErrorMsg009", msg.getSignupErrorMsg(9));
				return "option.jsp";
			}
		}

		int birthYear = Integer.parseInt(request.getParameter("birth-year"));
		int birthMonth = Integer.parseInt(request.getParameter("birth-month"));
		int birthDay = Integer.parseInt(request.getParameter("birth-day"));
		//		バラバラにとってきた３つをつなげる
		String birthdateString = birthYear + "-" + birthMonth + "-" + birthDay;
		//		String型→util.Date→sql.Date
		//		sqlへ入れるためのキャスト
		java.util.Date birthdateUtil = dateFormat.parse(birthdateString);
		java.sql.Date birthdate = new java.sql.Date(birthdateUtil.getTime());

		//		マイページのアカウント設定
		//		ユーザーネーム編
		if (request.getParameter("optionId") != null && request.getParameter("optionId").equals("profile")) {
			User user = (User) session.getAttribute("user");

			//			鰹節
			if (user == null) {
				return "login.jsp";
			}
			//			dbの重複チェック
			try {
				//				重複してたら0が入る
				duplicationErrorMessageList = dao.duplicationCheckForUsername(newUsername);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("signupErrorMsg", msg.getSignupErrorMsg(7));
			}

			//			正規表現と照合
			List<Integer> errorMessageList = new ArrayList<>();
			//			正規表現と合わなかったら2が入る
			errorMessageList = Valid.validCheckForUsername(newUsername);
			errorMessageList.addAll(duplicationErrorMessageList);

			//			問題があればメッセージを返す
			if (errorMessageList.size() != 0) {
				for (int i : errorMessageList) {
					request.setAttribute("signupErrorMsg00" + i, msg.getSignupErrorMsg(i));
				}
				return "option.jsp";
			}

			//			問題なければ登録し再ログイン
			if (dao.insertProfile(newUsername, sex, birthdate, user.getId()) > 0) {
				user = dao.login(newUsername, user.getPassword());
				session.setAttribute("user", user);
				request.setAttribute("completeMsg", msg.getCompleteMsg(4));
				return "message.jsp";
			} else {
				request.setAttribute("signupErrorMsg", msg.getSignupErrorMsg(7));
				return "option.jsp";
			}
		}

		//		ここから新規登録
		session.removeAttribute("notTrueFinalRealuser");
		try {
			duplicationErrorMessageList = dao.duplicationCheck(username, mailaddress);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//		次に正規表現と照合
		List<Integer> errorMessageList = new ArrayList<>();
		errorMessageList = Valid.validCheck(username, password, lastname, firstname, mailaddress);
		errorMessageList.addAll(duplicationErrorMessageList);
		//		errorMessageListリストに入った数字でエラーメッセージを呼び出す

		if (errorMessageList.size() != 0) {
			for (int i : errorMessageList) {
				request.setAttribute("signupErrorMsg00" + i, msg.getSignupErrorMsg(i));
			}
			return "signup.jsp";
		}

		//入力内容に問題がなければbeanに入れて確認画面へ飛ばす
		if (errorMessageList.size() == 0) {
			//		beanへ格納
			User notTrueFinalRealuser = new User(username, password, lastname, firstname, sex, birthdate, mailaddress);
			session.setAttribute("notTrueFinalRealuser", notTrueFinalRealuser);
		}
		return "confirm.jsp";
	}
}
