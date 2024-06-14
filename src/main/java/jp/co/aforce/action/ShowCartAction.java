package jp.co.aforce.action;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.beans.Cart;
import jp.co.aforce.beans.User;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.tool.Action;

public class ShowCartAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ProductDAO dao = new ProductDAO();
		User user = new User();
		user = (User) session.getAttribute("user");
		if (user != null) {
			List<Integer> inCartList = dao.getInCartList(user.getId());
			List<Cart> cartInfo = new ArrayList<>();
			for (Integer songId : inCartList) {
				Cart cart = dao.getCartInfo(songId);
				cartInfo.add(cart);
			}
			if (cartInfo == null || cartInfo.size() == 0) {
				return "cart.jsp";
			}
			request.setAttribute("cartInfo", cartInfo);
			return "cart.jsp";
		}
		return "login.jsp";
	}
}