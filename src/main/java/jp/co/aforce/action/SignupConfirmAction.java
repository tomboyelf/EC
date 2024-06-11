package jp.co.aforce.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.beans.User;
import jp.co.aforce.dao.UserDAO;
import jp.co.aforce.tool.Action;

public class SignupConfirmAction extends Action {
	public String execute(
				HttpServletRequest request, HttpServletResponse response
			) throws Exception{
		HttpSession session=request.getSession();
		response.setContentType("text/html; charset=UTF-8");
		
		
		
		User user=new User();
		user=(User) session.getAttribute("user");
		
		try {
			UserDAO dao=new UserDAO();
			
			//lineの使い道がない
			int line=dao.insert(user);
			
			return "index.jsp";
		} catch(Exception e) {
			e.printStackTrace();
			return "signup.jsp";
		}
		
	}

}
