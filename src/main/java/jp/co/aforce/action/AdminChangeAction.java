package jp.co.aforce.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.beans.User;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.tool.Action;

public class AdminChangeAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ProductDAO dao = new ProductDAO();
		User user = new User();
//		Message msg = new Message();
		user = (User) session.getAttribute("user");
		
		//		ログインチェック、鰹節
		if (user == null || user.getId() != 1) {
			//		ログインを促す
			return "login.jsp";
		}
		
		
		
		String[] albumId = request.getParameterValues("albumId");
		String[] categoryId = request.getParameterValues("categoryOption");
		System.out.println("albumIdの数：" + albumId.length);
		System.out.println("selectタグからとってきた値の数：" + categoryId.length);
//		if (categoryOption != null && categoryOption.length != 0) {
//			
//		}
		
		System.out.println("seikou");
		return "0";
		

	}
}