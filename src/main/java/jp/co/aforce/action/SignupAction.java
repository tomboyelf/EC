package jp.co.aforce.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.aforce.beans.User;
import jp.co.aforce.dao.UserDAO;
import jp.co.aforce.tool.Action;
import jp.co.aforce.tool.Message;
import jp.co.aforce.tool.Valid;

public class SignupAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		//		入力フォームの値を取得
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String lastname = request.getParameter("lastname");
		String firstname = request.getParameter("firstname");
		String sex = request.getParameter("sex");
		int birthYear = Integer.parseInt(request.getParameter("birth-year"));
		int birthMonth = Integer.parseInt(request.getParameter("birth-month"));
		int birthDay = Integer.parseInt(request.getParameter("birth-day"));
		String mailaddress = request.getParameter("mailaddress");

		//		先へ進む前に重複チェック
		UserDAO dao = new UserDAO();
		List<Integer> duplicationErrorMessageList = new ArrayList<>();
		try {
			duplicationErrorMessageList = dao.duplicationCheck(username, mailaddress);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//		次に正規表現と照合
		Valid valid = new Valid();
		List<Integer> errorMessageList = new ArrayList<>();
		errorMessageList = valid.validCheck(username, password, lastname, firstname, mailaddress);
		errorMessageList.addAll(duplicationErrorMessageList);
		//		errorMessageListリストに入った数字でエラーメッセージを呼び出す
		System.out.println(errorMessageList+"aa");

		if (errorMessageList.size() != 0) {
			Message msg = new Message();
			for (int i : errorMessageList) {
				request.setAttribute("signupErrorMsg00" + i, msg.getSignupErrorMsg(i));
			}
			return "signup.jsp";
		}
		if (errorMessageList.size() == 0) {
			//		バラバラにとってきた３つをつなげる
			String birthdateString = birthYear + "-" + birthMonth + "-" + birthDay;
			//		String型→util.Date→sql.Date
			//		sqlへ入れるためのキャスト
			java.util.Date birthdateUtil = dateFormat.parse(birthdateString);
			java.sql.Date birthdate = new java.sql.Date(birthdateUtil.getTime());

			//		beanへ格納
			User user = new User(username, password, lastname, firstname, sex, birthdate, mailaddress);
//			request.setAttribute("user", user);
		}
		return "signup-confirm.jsp";

		//		try {
		//			UserDAO dao=new UserDAO();
		//			int line=dao.insert(user);
		//			return "index.jsp";
		//		} catch(Exception e) {
		//			e.printStackTrace();
		//			return "signup.jsp";
		//		}
	}

}
